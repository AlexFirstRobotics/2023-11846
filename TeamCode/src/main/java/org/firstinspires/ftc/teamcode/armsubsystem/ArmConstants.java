package org.firstinspires.ftc.teamcode.armsubsystem;

public final class ArmConstants {

    public static class ARM_POSITIONS {
        public static class PIVOT_MOTOR {
            public static int HOME_POSITION = 0;
            public static int PICKUP_POSITION = -1000;
            public static int SCORE_LOW = 1000;
            public static int SCORE_MID = 2000;
            public static int SCORE_HIGH = 3000;
        }
        public static class EXTEND_MOTOR {
            public static int HOME_POSITION = 0;
            public static int PICKUP_POSITION = 1000;
            public static int SCORE_LOW = 1000;
            public static int SCORE_MID = 2000;
            public static int SCORE_HIGH = 3000;
        }
        public static class GRAB_SERVO {
            public static double RELEASE = 0.40;
            public static double GRAB = 0.50;
        }
        public static class PIVOT_SERVO {
            public static double HOME = 0.4;
            public static double PICKUP = 0.3;
            public static double SCORE_LOW = 0.5;
            public static double SCORE_MID = 0.5;
            public static double SCORE_HIGH = 0.5;

        }
    }

}