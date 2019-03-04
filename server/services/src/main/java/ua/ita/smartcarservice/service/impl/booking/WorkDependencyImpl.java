package ua.ita.smartcarservice.service.impl.booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.ita.smartcarservice.dto.booking.WorkInfoDto;
import ua.ita.smartcarservice.entity.booking.WorkDependency;
import ua.ita.smartcarservice.entity.technicalservice.WorkType;
import ua.ita.smartcarservice.repository.booking.WorkDependencyRepository;
import ua.ita.smartcarservice.repository.technicalservice.WorkTypeRepository;
import ua.ita.smartcarservice.service.booking.WorkDependencyService;
import ua.ita.smartcarservice.service.impl.booking.graph.Edge;
import ua.ita.smartcarservice.service.impl.booking.graph.Graph;
import ua.ita.smartcarservice.service.technicalservice.WorkTypeService;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class WorkDependencyImpl implements WorkDependencyService {
    private final int NUMBER_OF_WORKS = 60;

    @Autowired
    private WorkDependencyRepository workDependencyRepository;

    @Autowired
    private WorkTypeRepository workTypeRepository;

    @Autowired
    private WorkTypeService workTypeService;

    @Autowired
    private Graph graph;

    public List<WorkDependency> findAll() {
        return workDependencyRepository.findAll();
    }

    @Override
    public int findGraphSize() {
        return (int) workTypeRepository.findMaxId().longValue();
    }


    public WorkInfo findWorkInfo(List<String> skillName) {

        WorkInfo workInfo = new WorkInfo();

        ArrayList[] graph = this.graph.getGraph();

        PriorityQueue<Edge> allEdge = getAllEdgeInSortedPosition(skillName);

        Set<Long> needNode = getNeedNode(skillName);

        long masks[] = this.graph.getMasks();

        boolean was[] = new boolean[graph.length];

        int requiredTime = 0;

        while (!allEdge.isEmpty()) {
            long mask = 0;
            Edge mainEdge = allEdge.poll();
            if (was[(int) mainEdge.getIndex()]) {
                continue;
            }
            was[(int) mainEdge.getIndex()] = true;
            workInfo.setWorkInfo(new WorkInfoDto(mainEdge.getIndex(), requiredTime, (int)(requiredTime + mainEdge.getRequiredTime())));
            requiredTime += mainEdge.getRequiredTime();
            mask |= (1 << (mainEdge.getIndex()));
            for (int i = graph[(int) mainEdge.getIndex()].size() - 1; i >= 0; i--) {
                Edge dependentEdge = (Edge) graph[(int) mainEdge.getIndex()].get(i);
                if (was[(int) dependentEdge.getIndex()] || !needNode.contains(dependentEdge.getIndex())) {
                    continue;
                }
                boolean canAddToRes = true;
                for (int j = 1; j <= NUMBER_OF_WORKS; j++) {
                    if ((mask & (1 << j)) == 1 && (masks[(int) dependentEdge.getIndex()] & (1 << j)) != 1) {
                        canAddToRes = false;
                        break;
                    }
                }
                if (canAddToRes) {
                    was[(int) dependentEdge.getIndex()] = true;
                    workInfo.setWorkInfo(new WorkInfoDto(dependentEdge.getIndex(),
                            (int) (requiredTime - mainEdge.getRequiredTime()),
                            (int) (requiredTime - mainEdge.getRequiredTime() + dependentEdge.getRequiredTime())));
                    mask |= (1 << dependentEdge.getIndex());
                }
            }
        }
        workInfo.setRequiredTime(requiredTime);
        return workInfo;
    }


    private PriorityQueue<Edge> getAllEdgeInSortedPosition(List<String> skillsName) {
        Map<String, WorkType> workByName = getDistinctSkillByName();

        PriorityQueue<Edge> allEdge = new PriorityQueue <>(new Edge(-1, -1));

        skillsName.forEach(s -> allEdge.add(new Edge(workByName.get(s).getWorkId(), workByName.get(s).getRequiredTime())));

        return allEdge;
    }


    public Map<String, WorkType> getDistinctSkillByName() {
        return workTypeService.findDistinctWorkByName();
    }

    private Set<Long> getNeedNode(List <String> skillsName) {
        Map<String, WorkType> skillsByName = getDistinctSkillByName();

        return skillsName.stream().map(s -> skillsByName.get(s).getWorkId()).collect(Collectors.toSet());
    }
}
