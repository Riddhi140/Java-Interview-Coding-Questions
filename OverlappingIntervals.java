import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/*
 * Explanation : 1) Initialize the curr_start and curr_end variable with the 1st interval you encounter - interval[start,end]
 *    2) when you move to 2nd interval, check if 2nd interval timings collide with the curr_start and curr_end - if yes,
 *    then update curr_start as curr_start = min(curr_start, start of 2nd interval) and update curr_end as 
 *    curr_end = max(curr_end, end of 2nd interval)!!!!
 *    if no, then add the curr_start and curr_end to the result list and update curr_start = start of 2nd interval
 *    curr_end = end of 2nd interval
 *    3) Continue set 2 for rest of the intervals
 *    4) Return the list of merged intervals  
 */


public class OverlappingIntervals {

	public static void main(String[] args) {
		List<Intervals> list = new ArrayList<>();
		list.add(new Intervals(1, 3));
		list.add(new Intervals(8, 10));
		list.add(new Intervals(2, 6));
		list.add(new Intervals(7, 11));
		Collections.sort(list); //Sort the list in asc order of the start time of interval
		Merge(list);
	}

	private static void Merge(List<Intervals> list) {
		List<Intervals> result = new ArrayList<>();
		Iterator<Intervals> itr = list.iterator();
		int curr_end = 0;
		int curr_start = 0;
		while (itr.hasNext()) {
			Intervals inter = itr.next();
			if (curr_end==0 || ( inter.end > curr_end && inter.start > curr_start && inter.start < curr_end)) {
				curr_end = inter.end;
			}
			if (curr_start == 0 || (inter.start < curr_start && inter.end > curr_start && inter.end <curr_end)) {
				curr_start = inter.start;
			}
			if(curr_start>inter.start && curr_end<inter.end){
				curr_start = inter.start;
				curr_end = inter.end;
			}
			if(inter.start>curr_start && inter.start>curr_end || inter.start<curr_start && inter.end<curr_end){
				Intervals newInterval = new Intervals(curr_start, curr_end);
				result.add(newInterval);
				curr_end = inter.end;
				curr_start = inter.start;
			}

		}
		result.add(new Intervals(curr_start, curr_end));
		itr = result.iterator();
		while(itr.hasNext()){
			Intervals interc =itr.next();
			System.out.println(interc.start + " "+interc.end);
		}
	}
}

class Intervals implements Comparable<Intervals>{

	int start;
	int end;

	public Intervals(int start, int end) {
		super();
		this.start = start;
		this.end = end;
	}

	@Override
	public int compareTo(Intervals o) {
		
		return this.start-o.start;
	}

}