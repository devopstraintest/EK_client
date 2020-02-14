package connector.record;

import org.springframework.util.StopWatch;


public class Watcher extends StopWatch{

    public void report(){

        System.out.println( "\n[REPORT]**********************" );
        System.out.println( "Total time in milliseconds for all tasks : " + this.getTotalTimeMillis() + " ms");
        System.out.println( "\nArray of the data for tasks performed [Task Name] : [Time Taken]" );
        TaskInfo[] listofTasks = this.getTaskInfo();
        for ( TaskInfo task : listofTasks ) {
            System.out.format( "[%s]:[%d ms]\n",task.getTaskName(), task.getTimeMillis() );
        }
        System.out.println( "\n[REPORT_has_been_finished]*************" );

    }

}
