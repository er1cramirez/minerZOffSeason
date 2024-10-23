package frc.robot.subsystems;

import java.util.function.BooleanSupplier;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
// import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
// import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
public class Intake extends SubsystemBase {
    private final WPI_VictorSPX intake_Sup = new WPI_VictorSPX(13);
    private final WPI_VictorSPX intake_uno = new WPI_VictorSPX(11);
    private final WPI_VictorSPX intake_dos = new WPI_VictorSPX(12);



    public enum IntakeMode {
        STOP(0.0),
        INTAKING(0.8),        
        ALIGN(0.65),

        REVERSE(-0.1),
        SHOOT(0.75),
        CUSTOM(1.5);

        private double modeSpeed;

        private IntakeMode(double modeSpeed) {
            this.modeSpeed = modeSpeed;
        }
    }

    private IntakeMode intakeMode = IntakeMode.STOP;



    @Override
    public void periodic() {
        intakeRun(1);
    }

    private void intakeRun(double speed) {
        intake_Sup.set(speed);
        intake_uno.set(speed);
        intake_dos.set(speed);
    }
}
