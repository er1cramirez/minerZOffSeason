package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Intake;

public class TeleopIntake extends Command {
    private Intake intake;
    private DoubleSupplier intakeSpeed;
    public TeleopIntake(Intake intake, DoubleSupplier intakeSpeed) {
        this.intake = intake;
        addRequirements(intake);
        this.intakeSpeed = intakeSpeed;
    }
    
    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }
    
    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        double speed = intakeSpeed.getAsDouble();
        intake.intakeRun(speed);
    }
    
    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }
    
    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
    
}
