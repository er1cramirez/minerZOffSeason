package frc.lib;


import com.revrobotics.CANSparkBase;
import com.revrobotics.CANSparkLowLevel;

/** Sets motor usage for a Spark Max motor controller */
public class CANSparkUtil {
  public enum Usage {
    kAll,
    kPositionOnly,
    kVelocityOnly,
    kMinimal
  };

  /**
   * This function allows reducing a Spark Max's CAN bus utilization by reducing the periodic status
   * frame period of nonessential frames from 20ms to 500ms.
   *
   * <p>See
   * https://docs.revrobotics.com/sparkmax/operating-modes/control-interfaces#periodic-status-frames
   * for a description of the status frames.
   *
   * @param motor The motor to adjust the status frame periods on.
   * @param usage The status frame feedack to enable. kAll is the default when a CANSparkMax is
   *     constructed.
   * @param enableFollowing Whether to enable motor following.
   */
  public static void setCANSparkBusUsage(
      CANSparkBase motor, Usage usage, boolean enableFollowing) {
    if (enableFollowing) {
      motor.setPeriodicFramePeriod(CANSparkLowLevel.PeriodicFrame.kStatus0, 10);
    } else {
      motor.setPeriodicFramePeriod(CANSparkLowLevel.PeriodicFrame.kStatus0, 500);
    }

    if (usage == Usage.kAll) {
      //sets ussage to send all the frames of data yay
      motor.setPeriodicFramePeriod(CANSparkLowLevel.PeriodicFrame.kStatus1, 20);
      motor.setPeriodicFramePeriod(CANSparkLowLevel.PeriodicFrame.kStatus2, 20);
      motor.setPeriodicFramePeriod(CANSparkLowLevel.PeriodicFrame.kStatus3, 500);
    } else if (usage == Usage.kPositionOnly) {
      //only sends the position frames every 20 ms, saves on velocity and other status
      motor.setPeriodicFramePeriod(CANSparkLowLevel.PeriodicFrame.kStatus1, 1000);
      motor.setPeriodicFramePeriod(CANSparkLowLevel.PeriodicFrame.kStatus2, 20);
      motor.setPeriodicFramePeriod(CANSparkLowLevel.PeriodicFrame.kStatus3, 1000);
    } else if (usage == Usage.kVelocityOnly) {
      //only sends the velocity every 20 ms
      motor.setPeriodicFramePeriod(CANSparkLowLevel.PeriodicFrame.kStatus1, 20);
      motor.setPeriodicFramePeriod(CANSparkLowLevel.PeriodicFrame.kStatus2, 1000);
      motor.setPeriodicFramePeriod(CANSparkLowLevel.PeriodicFrame.kStatus3, 1000);
    } else if (usage == Usage.kMinimal) {
      //sends as little data as possible to save canbus ussage
      motor.setPeriodicFramePeriod(CANSparkLowLevel.PeriodicFrame.kStatus1, 500);
      motor.setPeriodicFramePeriod(CANSparkLowLevel.PeriodicFrame.kStatus2, 500);
      motor.setPeriodicFramePeriod(CANSparkLowLevel.PeriodicFrame.kStatus3, 500);
    }
  }

  /**
   * This function allows reducing a Spark Max's CAN bus utilization by reducing the periodic status
   * frame period of nonessential frames from 20ms to 500ms.
   *
   * <p>See
   * https://docs.revrobotics.com/sparkmax/operating-modes/control-interfaces#periodic-status-frames
   * for a description of the status frames.
   *
   * @param motor The motor to adjust the status frame periods on.
   * @param usage The status frame feedack to enable. kAll is the default when a CANSparkMax is
   *     constructed.
   */
  public static void setCANSparkBusUsage(CANSparkBase motor, Usage usage) {
    setCANSparkBusUsage(motor, usage, false);
  }
}