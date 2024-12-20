// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
// import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.TeleopIntake;
// import frc.robot.commands.TeleopShooter;
import frc.robot.commands.TeleopSwerve;
import frc.robot.autos.testAuto;
import frc.robot.commands.TeleopAmpliShoot;
import frc.robot.subsystems.AmpliShoot;
import frc.robot.subsystems.Intake;
// import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.SwerveSubsystem;
import edu.wpi.first.wpilibj.XboxController;


public class RobotContainer {
  private final XboxController driveController = new XboxController(0);
// private final CommandXboxController driverXbox = new CommandXboxController(0);
  private final XboxController robotController = new XboxController(1);
  /*Drive Controls*/
  private final int translationAxis = XboxController.Axis.kLeftY.value;
  private final int strafeAxis = XboxController.Axis.kLeftX.value;
  private final int rotationAxis = XboxController.Axis.kRightX.value;
  
  private final Trigger robotCentric = new JoystickButton(driveController,XboxController.Button.kY.value);
  private final Trigger xButton = new JoystickButton(driveController, XboxController.Button.kX.value);
  private final Trigger bButton = new JoystickButton(driveController, XboxController.Button.kB.value);

  private final Trigger shootButton = new JoystickButton(robotController, XboxController.Button.kA.value);
  /*Subsystems*/
  private final SwerveSubsystem swerveDrive = new SwerveSubsystem();
  private final Intake intake = new Intake();
  private final AmpliShoot shooter = new AmpliShoot();
  // private final ShooterSubsystem PIDcontrolledShooter = new ShooterSubsystem();
  /** The container for the robot. Contains subsystems, OI devices, and commands.*/

  Command autCommand;
  public RobotContainer() {
    swerveDrive.setDefaultCommand(
      new TeleopSwerve(
          swerveDrive,
          () -> -driveController.getRawAxis(translationAxis),
          () -> driveController.getRawAxis(strafeAxis),
          () -> -driveController.getRawAxis(rotationAxis),
          () -> robotCentric.getAsBoolean()));
    intake.setDefaultCommand(
      new TeleopIntake(intake, () -> robotController.getRawAxis(XboxController.Axis.kLeftY.value)));
    shooter.setDefaultCommand(
      new TeleopAmpliShoot(shooter, () -> shootButton.getAsBoolean()));

    // PIDcontrolledShooter.setDefaultCommand(
    //   new TeleopShooter(PIDcontrolledShooter, () -> robotController.getRawAxis(XboxController.Axis.kRightY.value)));
    autCommand = new testAuto(swerveDrive, shooter);
    configureBindings();
  }
  private void configureBindings() {
    bButton.onTrue(new InstantCommand(() -> swerveDrive.zeroGyro()));
    xButton.onTrue(new InstantCommand(() -> swerveDrive.setWheelsToX()));
  }
  public Command getAutonomousCommand() {
    // return null;
    return autCommand;
  }

}
