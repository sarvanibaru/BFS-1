// Time Complexity : O(V + E) / O(N + P)
// Space Complexity : O(V + E) / O(N + P)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

// Your code here along with comments explaining your approach
/*
when we try to draw the dependencies of this problem, we can see its a graph and idea is to identify if
there is a cycle present.If cycle is present, we cant finish all courses.So, firstly, we construct an adjacency
map and have an indegree array to optimize the traversal.Then, we have a queue and add the independent courses
first and then traverse through that course's dependent courses using adjacency map and add those courses to
the queue if they become independent.At last, we check if our count has reached to the required number of courses or not.
 */
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] indegree = new int[numCourses];
        Map<Integer, List<Integer>> map = new HashMap<>();

        //arr[0] - dependent course, arr[1] - independent course
        for(int[] arr : prerequisites) {
            //increment dependent course values
            indegree[arr[0]]++;
            //map of independent to dependent courses
            map.putIfAbsent(arr[1], new ArrayList<>());
            map.get(arr[1]).add(arr[0]);
        }

        Queue<Integer> queue = new LinkedList<>();
        int count = 0;

        //add independent courses first to the queue (behaviour like root)
        for(int i = 0 ; i < indegree.length ; i++) {
            if(indegree[i] == 0) {
                queue.add(i);
                count++;
            }
        }

        //no independent courses at all -> cycle
        if(queue.isEmpty())
            return false;
        //all are independent courses
        if(count == numCourses)
            return true;

        while(!queue.isEmpty()) {
            int course = queue.poll();
            List<Integer> dependentList = map.get(course);
            if(dependentList != null) {
                for(int dependent : dependentList) {
                    indegree[dependent]--;
                    if(indegree[dependent] == 0) {
                        queue.add(dependent);
                        count++;
                    }
                }
            }
        }
        return count == numCourses ? true : false;
    }
}