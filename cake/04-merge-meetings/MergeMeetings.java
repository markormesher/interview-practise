/*
Task: merge all overlapping meetings.

Eg:
In: [0, 3], [2, 5], [7, 9], [8, 11]
Out: [0, 5], [7, 11]
*/

import java.util.*;

public class MergeMeetings {

	public static boolean meetingsOverlap(Meeting m1, Meeting m2) {
		return !(m1.start > m2.end || m2.start > m1.end);
	}

	public static ArrayList<Meeting> mergeMeetings(ArrayList<Meeting> meetings) {
		// sort meeting list
		Collections.sort(meetings, new Comparator<Meeting>() {
			public int compare(Meeting m1, Meeting m2)  {
				return m1.start - m2.start;
			}
		});

		// create output for new meetings
		ArrayList<Meeting> merged = new ArrayList<>();

		// loop through meetings, merging those that overlap
		Meeting current = meetings.get(0);
		for (int i = 1, n = meetings.size(); i < n; ++i) {
			if (meetingsOverlap(current, meetings.get(i))) {
				current.end = Math.max(current.end, meetings.get(i).end);
			} else {
				merged.add(current);
				current = meetings.get(i);
			}
		}
		merged.add(current);

		return merged;
	}

	public static void main(String... args) {
		ArrayList<ArrayList<Meeting>> meetings = new ArrayList<>();
		meetings.add(new ArrayList<Meeting>(){{
			add(new Meeting(0, 4));
			add(new Meeting(1, 5));
		}});
		meetings.add(new ArrayList<Meeting>(){{
			add(new Meeting(0, 4));
			add(new Meeting(4, 8));
		}});
		meetings.add(new ArrayList<Meeting>(){{
			add(new Meeting(6, 7));
			add(new Meeting(5, 8));
			add(new Meeting(0, 1));
		}});
		meetings.add(new ArrayList<Meeting>(){{
			add(new Meeting(0, 1));
			add(new Meeting(1, 2));
			add(new Meeting(2, 3));
			add(new Meeting(3, 4));
			add(new Meeting(4, 5));
		}});
		meetings.add(new ArrayList<Meeting>(){{
			add(new Meeting(4, 5));
			add(new Meeting(3, 4));
			add(new Meeting(2, 3));
			add(new Meeting(1, 2));
			add(new Meeting(0, 1));
		}});

		for (int i = 0; i < meetings.size(); ++i) {
			System.out.println("#" + i + " In: " + meetings.get(i));
			System.out.println("#" + i + " Out: " + mergeMeetings(meetings.get(i)));
			System.out.println();
		}
	}

	public static class Meeting {
		int start;
		int end;
		public Meeting(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public String toString() {
			return "[" + start + ", " + end + "]";
		}
	}

}