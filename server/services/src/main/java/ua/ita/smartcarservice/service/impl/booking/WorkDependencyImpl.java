package ua.ita.smartcarservice.service.impl.booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.ita.smartcarservice.entity.booking.WorkDependency;
import ua.ita.smartcarservice.entity.technicalservice.WorkType;
import ua.ita.smartcarservice.repository.booking.WorkDependencyRepository;
import ua.ita.smartcarservice.repository.technicalservice.WorkTypeRepository;
import ua.ita.smartcarservice.service.booking.WorkDependencyService;
import ua.ita.smartcarservice.service.impl.booking.Graph.Edge;
import ua.ita.smartcarservice.service.impl.booking.Graph.Graph;
import ua.ita.smartcarservice.service.technicalservice.WorkTypeService;

import java.util.*;

@Service
public class WorkDependencyImpl implements WorkDependencyService {

    @Autowired
    private WorkDependencyRepository workDependencyRepository;

    @Autowired
    private WorkTypeRepository workTypeRepository;

    @Autowired
    private WorkTypeService workTypeService;

    @Autowired
    private Graph graph;

    public List<WorkDependency> findAll(){
        return workDependencyRepository.findAll();
    }

    @Override
    public int findGraphSize(){
        return (int)workTypeRepository.findMaxId().longValue();
    }



    public int findRequiredTime(List<String> skillName) {

        ArrayList[] graph = this.graph.getGraph();

        PriorityQueue <Edge> allEdge = getAllEdgeInSortedPosition(skillName);

        Set <Long> needNode = getNeedNode(skillName);

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
            requiredTime += mainEdge.getRequiredTime();
            mask |= (1 << (mainEdge.getIndex()));
            for (int i = graph[(int) mainEdge.getIndex()].size() - 1; i >= 0; i--) {
                Edge dependentEdge = (Edge)graph[(int) mainEdge.getIndex()].get(i);
                if (was[(int) dependentEdge.getIndex()] || !needNode.contains(dependentEdge.getIndex())) {
                    continue;
                }
                boolean canAddToRes = true;
                for (int j = 1; j <= 60; j++) {
                    if ((mask & (1 << j)) == 1 && (masks[(int) dependentEdge.getIndex()] & (1 << j)) != 1) {
                        canAddToRes = false;
                        break;
                    }
                }
                if (canAddToRes) {
                    was[(int) dependentEdge.getIndex()] = true;
                    mask |= (1 << dependentEdge.getIndex());
                }
            }
        }
        return requiredTime;
    }


    private PriorityQueue<Edge> getAllEdgeInSortedPosition(List<String> skillName){
        Map<String, WorkType> workByName = getDistinctSkillByName();

        PriorityQueue<Edge> allEdge = new PriorityQueue<>(new Edge(-1, -1));

        skillName.forEach(s -> allEdge.add(new Edge(workByName.get(s).getWorkId(), workByName.get(s).getRequiredTime())));

        return allEdge;
    }


    public Map<String, WorkType> getDistinctSkillByName(){
        return workTypeService.findDistinctWorkByName();
    }

    private Set<Long> getNeedNode(List<String> skillName){
        Map<String, WorkType> skillByName = getDistinctSkillByName();

        Set<Long> needNode = new HashSet <>();

        skillName.forEach(s -> needNode.add(skillByName.get(s).getWorkId()));

        return needNode;
    }
}
