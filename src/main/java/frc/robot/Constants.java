// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.CANSparkBase.IdleMode;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.math.util.Units;
import frc.lib.SwerveModuleConstants;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static final class SwerveConstants{
    public static final double inputDeadband = .1;
    public static final boolean invertNavx = false;

    public static final double trackWidth = Units.inchesToMeters(22.835);
    public static final double wheelBase = Units.inchesToMeters(22.835);
    public static final double wheelDiameter = Units.inchesToMeters(3.937);
    public static final double wheelCircumference = wheelDiameter * Math.PI;

    public static final double openLoopRamp = 0.25;
    public static final double closedLoopRamp = 0.0;

    public static final double driveGearRatio = (6.75 / 1.0); 
    public static final double angleGearRatio = ((150/7) / 1.0);
    
    public static final double frontLeftOffset = -0.27099609375;//0.2099609375; //1
    public static final double backLeftOffset = 0.28515625;//0.13232421875; //3
    public static final double frontRightOffset = -0.220947265625;//360.0 - 0.328369140625; //0
    public static final double backRightOffset = 0.36474609375;//360.0 + 0.05126953125; //2

    public static final SwerveDriveKinematics swerveKinematics =
    new SwerveDriveKinematics(
        new Translation2d(wheelBase / 2.0, trackWidth / 2.0), 
        new Translation2d(wheelBase / 2.0, -trackWidth / 2.0),
        new Translation2d(-wheelBase / 2.0, trackWidth / 2.0),
        new Translation2d(-wheelBase / 2.0, -trackWidth / 2.0));

    public static final double voltageComp = 12.0;

    public static final int angleContinuousCurrentLimit = 15; //limits current draw of turning motor
    public static final int driveContinuousCurrentLimit = 40; //limits current draw of drive motor
  


    /* Drive Motor PID Values */
    public static final double driveKP = 0.1; //to tune
    public static final double driveKI = 0.0; //to tune
    public static final double driveKD = 0.0; //to tune
    public static final double driveKFF = 0.0; //to tune

    /* Drive Motor Characterization Values */
    //values to calculate the drive feedforward (KFF)
    public static final double driveKS = 0.667; //to calculate
    public static final double driveKV = 2.44; //to calculate
    public static final double driveKA = 0.27; //to calculate

    /* Drive Motor Conversion Factors */
    public static final double driveConversionPositionFactor = (wheelDiameter * Math.PI) / driveGearRatio;
    public static final double driveConversionVelocityFactor = driveConversionPositionFactor / 60.0;
    public static final double angleConversionFactor = 360.0 / angleGearRatio;

    /* Swerve Profiling Values */
    public static final double maxSpeed = 3; // meters per second
    public static final double maxAngularVelocity = 1.5; //what are these units?

    /* Neutral Modes */ 
    public static final IdleMode angleNeutralMode = IdleMode.kBrake; //change to break
    public static final IdleMode driveNeutralMode = IdleMode.kBrake; //change to break

    /* Motor Inverts */
    public static final boolean driveInvert = false;
    public static final boolean angleInvert = false;

    /* Angle Encoder Invert */
    public static final boolean canCoderInvert = false;
    

   

    public static final class FrontLeftMod {
      public static final int moudleId = 1;
      public static final int driveMotorID = 1; 
      public static final int angleMotorID = 2; 
      public static final int canCoderID = 16;
      public static final Rotation2d angleOffset = Rotation2d.fromDegrees(frontLeftOffset);
    
      /* Angle Motor PID Values */
      public static final double angleKP = 0.01; 
      public static final double angleKI = 0.0; 
      public static final double angleKD = 0.0; 
      public static final double angleKFF = 0.0; 
    
      public static final SwerveModuleConstants constants =
          new SwerveModuleConstants(driveMotorID, angleMotorID, canCoderID, angleOffset, angleKP, angleKI, angleKD, angleKFF);
          //creates a constant with all info from swerve module
    }

    public static final class BackLeftMod {
      public static final int moudleId = 3;
      public static final int driveMotorID = 3;
      public static final int angleMotorID = 4;
      public static final int canCoderID = 17;
      public static final Rotation2d angleOffset = Rotation2d.fromDegrees(backLeftOffset); 
      
      /* Angle Motor PID Values */
      public static final double angleKP = 0.01; //to tune
      public static final double angleKI = 0.0; //to tune
      public static final double angleKD = 0.0; //to tune
      public static final double angleKFF = 0.0; //to tune
  
      public static final SwerveModuleConstants constants =
        new SwerveModuleConstants(driveMotorID, angleMotorID, canCoderID, angleOffset, angleKP, angleKI, angleKD, angleKFF);
        //creates a constant with all info from swerve module
    }


    public static final class FrontRightMod {
      public static final int moudleId = 0;
      public static final int driveMotorID = 5;
      public static final int angleMotorID = 6;
      public static final int canCoderID = 19;
      public static final Rotation2d angleOffset = Rotation2d.fromDegrees(frontRightOffset);
      /* Angle Motor PID Values */
      public static final double angleKP = 0.01; //to tune
      public static final double angleKI = 0.0; //to tune
      public static final double angleKD = 0.0; //to tune
      public static final double angleKFF = 0.0; //to tune
        
      public static final SwerveModuleConstants constants =
          new SwerveModuleConstants(driveMotorID, angleMotorID, canCoderID, angleOffset, angleKP, angleKI, angleKD, angleKFF);
          //creates a constant with all info from swerve module
    }

    public static final class BackRightMod {
      public static final int moudleId = 2;
      public static final int driveMotorID = 7;
      public static final int angleMotorID = 8;
      public static final int canCoderID = 18 ;
      public static final Rotation2d angleOffset = Rotation2d.fromDegrees(backRightOffset);
        /* Angle Motor PID Values */
      public static final double angleKP = 0.01; //to tune
      public static final double angleKI = 0.0; //to tune
      public static final double angleKD = 0.0; //to tune
      public static final double angleKFF = 0.0; //to tune
    
      public static final SwerveModuleConstants constants =
          new SwerveModuleConstants(driveMotorID, angleMotorID, canCoderID, angleOffset, angleKP, angleKI, angleKD, angleKFF);
          //creates a constant with all info from swerve module
    }
    

    public static final boolean angleMotorInvert = false;
    public static final boolean driveMotorInvert = false;

    



  }

  public static final class AutoConstants {
    public static final double kMaxSpeedMetersPerSecond = 1.75;
    public static final double kMaxAccelerationMetersPerSecondSquared = 2;
    public static final double kMaxAngularSpeedRadiansPerSecond = Math.PI;
    public static final double kMaxAngularSpeedRadiansPerSecondSquared = Math.PI;

    public static final double kPXController = 1;
    public static final double kPYController = 1;
    public static final double kPThetaController = 1;

    // Constraint for the motion profilied robot angle controller
    public static final TrapezoidProfile.Constraints kThetaControllerConstraints =
        new TrapezoidProfile.Constraints(
            kMaxAngularSpeedRadiansPerSecond, kMaxAngularSpeedRadiansPerSecondSquared);
  }



}
