// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;

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
  // The robot's subsystems and commands are defined here...

  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final XboxController m_XboxController = new XboxController(0);
  //private final CommandJoystick m_JoystickL = new CommandJoystick(0);
  //private final CommandJoystick m_JoystickR = new CommandJoystick(1);

  /* Drive Controls */
  private final int translationAxis = XboxController.Axis.kLeftY.value;
  private final int strafeAxis = XboxController.Axis.kLeftX.value;
  private final int rotationAxis = XboxController.Axis.kRightX.value;
  
  private final Trigger robotCentric = new JoystickButton(m_XboxController,XboxController.Button.kY.value);
  private final Trigger xButton = new JoystickButton(m_XboxController, XboxController.Button.kX.value);
  private final Trigger bButton = new JoystickButton(m_XboxController, XboxController.Button.kB.value);



  /*private final int translationAxis = Joystick.AxisType.kY.value; //left flight stick
  private final int strafeAxis = Joystick.AxisType.kX.value; //left flight stick
  private final int rotationAxis = Joystick.AxisType.kX.value; //right flight stick*/

  /* Subsystems */
  private final SwerveSubsystem m_SwerveSubsystem = new SwerveSubsystem();



  
  
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    m_SwerveSubsystem.setDefaultCommand(
      new TeleopSwerve(
          m_SwerveSubsystem,
          () -> -m_XboxController.getRawAxis(translationAxis),
          () -> m_XboxController.getRawAxis(strafeAxis),
          () -> -m_XboxController.getRawAxis(rotationAxis),
          () -> robotCentric.getAsBoolean()));

    configureBindings();
  }

 
  private void configureBindings() {
    bButton.onTrue(new InstantCommand(() -> m_SwerveSubsystem.zeroGyro()));
    xButton.onTrue(new InstantCommand(() -> m_SwerveSubsystem.setWheelsToX()));
   
  }

 
  public Command getAutonomousCommand() {
    return null;
  }
}
