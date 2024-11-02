package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
// import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
// import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
public class Intake extends SubsystemBase {
    private final WPI_VictorSPX intake_Sup = new WPI_VictorSPX(13);
    private final WPI_VictorSPX intake_uno = new WPI_VictorSPX(11);
    private final WPI_VictorSPX intake_dos = new WPI_VictorSPX(12);

    @Override
    public void periodic() {
        // intakeRun(1);
    }

    public void intakeRun(double speed) {
        intake_Sup.set(-speed);
        intake_uno.set(speed);
        intake_dos.set(speed);
    }
}
