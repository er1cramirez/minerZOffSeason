package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class AmpliShoot extends SubsystemBase {
    private final WPI_VictorSPX intake_Sup = new WPI_VictorSPX(13);
    private final CANSparkMax lanzador_U_Sup = new CANSparkMax(10,MotorType.kBrushless);
    private final CANSparkMax lanzador_D_Sup = new CANSparkMax(9,MotorType.kBrushless);
    // private final WPI_VictorSPX intake_uno = new WPI_VictorSPX(11);
    // private final WPI_VictorSPX intake_dos = new WPI_VictorSPX(12);                  


    
    public AmpliShoot() {
    }
    @Override
    public void periodic() {
    }
    public void runShooter(double speed) {
        lanzador_U_Sup.set(speed);
        lanzador_D_Sup.set(-speed);
    }
    public void feederRun(double speed) {
        intake_Sup.set(speed);
    }
    
}
