package AlgorithmVisualizer.LogTracer;

public class TimeTaken {
    
    private static long Time = 0, time = 0;
    
    public static String time(boolean save) {
        long t = System.currentTimeMillis();
        if(save)
            Time += t-time;
        else
            time = t;
        int h = (int)(((t - Time) / 1000) / 3600),
            m = (int)((((t - Time) / 1000) % 3600) / 60),
            s = (int)((((t - Time) / 1000) % 3600) %60),
            ms = (int)((t - Time) % 1000);
        String H = ((h<10)?"0":"") + h,
               M = ((m<10)?"0":"") + m,
               S = ((s<10)?"0":"") + s,
               MS = "00" + ms;
        MS = MS.substring(MS.length() - 3);
        return H + ":" + M + ":" + S + "." + MS;
    }
    
    public static void resetTime() {
        Time = 0;
        time = 0;
    }
}
