package ua.ita.smartcarservice.service.impl.booking.Graph;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.ita.smartcarservice.entity.booking.SkillDependency;
import ua.ita.smartcarservice.entity.technicalservice.SkillEntity;
import ua.ita.smartcarservice.service.booking.SkillDependencyService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component("Graph")
public class Graph{

    @Autowired
    private SkillDependencyService skillDependencyService;

    private long[] masks;
    private ArrayList[] graph;

    public ArrayList[] getGraph() {
        if(graph == null){
            graph = createGraph();
        }
        return graph;
    }

    public long[] getMasks() {
        return masks;
    }

    private ArrayList[] createGraph(){

        System.out.println("CREATE GRAPH");
        int graphSize = skillDependencyService.findGraphSize();

        Map<String, SkillEntity> requiredTime = skillDependencyService.getDistinctSkillByName();

        List<SkillDependency> graphNode = skillDependencyService.findAll();

        graph = new ArrayList[graphSize + 1];
        for(int i = 0; i<graph.length;i++){
            graph[i] = new ArrayList();
        }
        masks = new long[graphSize + 1];

        graphNode.forEach(node ->{
            graph[(int)node.getMainSkill().getSkillId().longValue()]
                    .add(new Edge(node.getDependentSkill().getSkillId(),
                            requiredTime.get(node.getDependentSkill().getName()).getRequiredTime()));

            graph[(int)node.getDependentSkill().getSkillId().longValue()]
                    .add(new Edge(node.getMainSkill().getSkillId(),
                            requiredTime.get(node.getMainSkill().getName()).getRequiredTime()));

            masks[(int)node.getMainSkill().getSkillId().longValue()]|=(int)node.getDependentSkill().getSkillId().longValue();
        });

        return graph;
    }
}
