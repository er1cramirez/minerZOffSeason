package frc.robot.autos;

import java.util.List;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.SwerveControllerCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
// import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
// import edu.wpi.first.wpilibj2.command.WaitCommand;
// import edu.wpi.first.wpilibj2.command.WaitUntilCommand;
import frc.robot.Constants;
// import frc.robot.commands.AutoAmpliShoot;
import frc.robot.subsystems.AmpliShoot;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.SwerveSubsystem;

public class testAuto extends SequentialCommandGroup{
    public testAuto(SwerveSubsystem m_SwerveSubsystem, AmpliShoot ampliShoot, Intake intake) {
    TrajectoryConfig config =
        new TrajectoryConfig(
                Constants.AutoConstants.kMaxSpeedMetersPerSecond,
                Constants.AutoConstants.kMaxAccelerationMetersPerSecondSquared)
            .setKinematics(Constants.SwerveConstants.swerveKinematics);

    Trajectory outTrajectory =
        TrajectoryGenerator.generateTrajectory(
            // Start at the origin facing the +X direction
            new Pose2d(0, 0, new Rotation2d(0)),
            // Pass through these  interior waypoints
            List.of(new Translation2d(-0.5, 1)), 
            // End 1.5 meters straight ahead of where we started, facing forward
            new Pose2d(-0.5, 1.1, new Rotation2d(0)),
            config);

    var thetaController =
        new ProfiledPIDController(
            Constants.AutoConstants.kPThetaController,
            0,
            0,
            Constants.AutoConstants.kThetaControllerConstraints);
    thetaController.enableContinuousInput(-Math.PI, Math.PI);

    SwerveControllerCommand swerveControllerCommand =
        new SwerveControllerCommand(
            outTrajectory,
            m_SwerveSubsystem::getPose,
            Constants.SwerveConstants.swerveKinematics,
            new PIDController(Constants.AutoConstants.kPXController, 0, 0),
            new PIDController(Constants.AutoConstants.kPYController, 0, 0),
            thetaController,
            m_SwerveSubsystem::setModuleStates,
            m_SwerveSubsystem);
    // AutoAmpliShoot ampliShoot2 = new AutoAmpliShoot(ampliShoot);
    // addSequential(new InstantCommand(() -> ampliShoot.runShooter(0.4)));

    addCommands(
        // new WaitUntilCommand(new AutoAmpliShoot(ampliShoot)),
        new InstantCommand(() -> ampliShoot.runShooter(0.4)),
        new WaitCommand(0.5),
        new InstantCommand(() -> ampliShoot.runShooter(0.6)),
        new WaitCommand(0.8),
        new InstantCommand(() -> ampliShoot.runShooter(0.8)),
        new WaitCommand(0.1),
        new InstantCommand(() -> intake.feederRun(1)),
        new WaitCommand(1.2),
        new InstantCommand(() -> ampliShoot.runShooter(0)),
        new WaitCommand(0.1),
        new InstantCommand(() -> intake.feederRun(0)),
        new WaitCommand(1),
        new InstantCommand(() -> m_SwerveSubsystem.resetOdometry(outTrajectory.getInitialPose())),
        swerveControllerCommand);
    }
}


// if(ampliStatus != 10){
//             if(ampliStatus == 0){
//                 ampliShoot.runShooter(0.1);
//                 if((Timer.getFPGATimestamp()- incio_ampli)> 0.5) ampliStatus = 1;
//             }
//             if(ampliStatus == 1){
//                 ampliShoot.runShooter(0.3);
//                 if((Timer.getFPGATimestamp()- incio_ampli)> 0.5) ampliStatus = 2;
//             }
//             if(ampliStatus == 2){
//                 ampliShoot.runShooter(0.5);
//                 if((Timer.getFPGATimestamp()- incio_ampli)> 1.5) ampliStatus = 3;
//             }
//             if(ampliStatus == 3){
//                 ampliShoot.runShooter(0.7);
//                 ampliShoot.feederRun(0.5);
//                 if((Timer.getFPGATimestamp()- incio_ampli)> 2) ampliStatus = 4;
//             }
//             if(ampliStatus == 4){
//                 ampliShoot.runShooter(0);
//                 ampliShoot.feederRun(0);
//                 if((Timer.getFPGATimestamp()- incio_ampli)> 2) ampliStatus = 10;
//             }
//         }