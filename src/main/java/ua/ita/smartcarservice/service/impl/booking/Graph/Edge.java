package ua.ita.smartcarservice.service.impl.booking.Graph;

import java.util.Comparator;

public class Edge implements Comparator {
    private long index;
    private long requiredTime;

    public Edge(long index, long requiredTime){
        this.index = index;
        this.requiredTime = requiredTime;
    }

    public long getIndex() {
        return index;
    }

    public long getRequiredTime() {
        return requiredTime;
    }
    @Override
    public int compare(Object o1, Object o2) {
        Edge e1 = (Edge)o1;
        Edge e2 = (Edge)o2;

        return Long.compare(e1.requiredTime, e2.requiredTime)* -1;
    }
}
