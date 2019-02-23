package ua.ita.smartcarservice.service.impl.booking;

import ua.ita.smartcarservice.service.impl.booking.Graph.Graph;
import ua.ita.smartcarservice.service.impl.booking.Graph.Edge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.ita.smartcarservice.entity.booking.SkillDependency;
import ua.ita.smartcarservice.entity.technicalservice.SkillEntity;
import ua.ita.smartcarservice.repository.booking.SkillDependencyRepository;
import ua.ita.smartcarservice.repository.technicalservice.SkillRepository;
import ua.ita.smartcarservice.service.booking.SkillDependencyService;
import ua.ita.smartcarservice.service.impl.technicalservice.SkillServiceImpl;
import ua.ita.smartcarservice.service.technicalservice.SkillService;

import java.util.*;

@Service
public class SkillDependencyImpl implements SkillDependencyService {

    @Autowired
    private SkillDependencyRepository skillDependencyRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private SkillService skillService;

    @Autowired
    private Graph graph;

    public List<SkillDependency> findAll(){
        return skillDependencyRepository.findAll();
    }

    @Override
    public int findGraphSize(){
        return (int)skillRepository.findMaxId().longValue();
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
        Map<String, SkillEntity> skillByName = getDistinctSkillByName();

        PriorityQueue<Edge> allEdge = new PriorityQueue<>(new Edge(-1, -1));

        skillName.forEach(s -> allEdge.add(new Edge(skillByName.get(s).getSkillId(), skillByName.get(s).getRequiredTime())));

        return allEdge;
    }


    public Map<String, SkillEntity> getDistinctSkillByName(){
        return skillService.findDistinctSkillByName();
    }

    private Set<Long> getNeedNode(List<String> skillName){
        Map<String, SkillEntity> skillByName = getDistinctSkillByName();

        Set<Long> needNode = new HashSet <>();

        skillName.forEach(s -> needNode.add(skillByName.get(s).getSkillId()));

        return needNode;
    }
}
