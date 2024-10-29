package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
// import frc.robot.subsystems.Intake;
import frc.robot.subsystems.ShooterSubsystem;

public class TeleopShooter extends Command {
    private ShooterSubsystem shooter;
    private DoubleSupplier shooterSpeed;
    public TeleopShooter(ShooterSubsystem shooter, DoubleSupplier shooterSpeed) {
        this.shooter = shooter;
        addRequirements(shooter);
        this.shooterSpeed = shooterSpeed;
    }
    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }
    
    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        double speed = 0.1 * shooterSpeed.getAsDouble();
        shooter.setSpeed(speed);
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
