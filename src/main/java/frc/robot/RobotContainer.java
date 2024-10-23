// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

import frc.robot.commands.TeleopSwerve;
import frc.robot.subsystems.SwerveSubsystem;

import com.ctre.phoenix6.mechanisms.swerve.SwerveModule;
import com.ctre.phoenix6.mechanisms.swerve.SwerveModuleConstants;

import edu.wpi.first.wpilibj.PS4Controller;
import edu.wpi.first.wpilibj.PS5Controller;
import edu.wpi.first.wpilibj.XboxController;





public class RobotContainer {
  private final XboxController driveController = new XboxController(0);
// private final CommandXboxController driverXbox = new CommandXboxController(0);
  private final CommandXboxController robotController = new CommandXboxController(1);

  /* Drive Controls */
  private final int translationAxis = XboxController.Axis.kLeftY.value;
  private final int strafeAxis = XboxController.Axis.kLeftX.value;
  private final int rotationAxis = XboxController.Axis.kRightX.value;
  
  private final Trigger robotCentric = new JoystickButton(driveController,XboxController.Button.kY.value);
  private final Trigger xButton = new JoystickButton(driveController, XboxController.Button.kX.value);
  private final Trigger bButton = new JoystickButton(driveController, XboxController.Button.kB.value);
  /* Subsystems */
  private final SwerveSubsystem swerveDrive = new SwerveSubsystem();



  
  
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    swerveDrive.setDefaultCommand(
      new TeleopSwerve(
          swerveDrive,
          () -> -driveController.getRawAxis(translationAxis),
          () -> driveController.getRawAxis(strafeAxis),
          () -> -driveController.getRawAxis(rotationAxis),
          () -> robotCentric.getAsBoolean()));
    configureBindings();
  }

 
  private void configureBindings() {
    bButton.onTrue(new InstantCommand(() -> swerveDrive.zeroGyro()));
    xButton.onTrue(new InstantCommand(() -> swerveDrive.onXButton()));
   
  }

 
  public Command getAutonomousCommand() {
    return null;
  }
}
