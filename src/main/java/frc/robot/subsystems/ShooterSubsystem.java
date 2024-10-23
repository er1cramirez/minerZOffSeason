package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkPIDController;
import com.revrobotics.CANSparkBase.ControlType;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.math.controller.SimpleMotorFeedforward;
// import edu.wpi.first.math.kinematics.SwerveModuleState;
import frc.lib.CANSparkMaxUtil;
import frc.lib.CANSparkMaxUtil.Usage;
// import edu.wpi.first.wpilibj.motorcontrol.Spark;
// import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import frc.robot.Constants;

public class ShooterSubsystem {
    private CANSparkMax upShooterMotor;
    private RelativeEncoder upShooterEncoder;
    private CANSparkMax downShooterMotor;
    private RelativeEncoder downShooterEncoder;
    // private final WPI_VictorSPX feederMotor = new WPI_VictorSPX(Constants.ShooterConstants.feederMotorID);

    private final SparkPIDController upShooterController;
    private final SparkPIDController downShooterController;

    private final SimpleMotorFeedforward feedforward =
        new SimpleMotorFeedforward(
            Constants.SwerveConstants.driveKS, Constants.SwerveConstants.driveKV, Constants.SwerveConstants.driveKA);

    public ShooterSubsystem() {
        upShooterMotor = new CANSparkMax(9, MotorType.kBrushless);
        downShooterMotor = new CANSparkMax(10, MotorType.kBrushless);

        upShooterEncoder = upShooterMotor.getEncoder();
        downShooterEncoder = downShooterMotor.getEncoder();

        upShooterController = upShooterMotor.getPIDController();
        downShooterController = downShooterMotor.getPIDController();

        configShooterMotor(upShooterMotor, upShooterEncoder, upShooterController);
        configShooterMotor(downShooterMotor, downShooterEncoder, downShooterController);

        upShooterMotor.setInverted(true);
        downShooterMotor.setInverted(false);

    }

    public void setSpeed(double speed) {
        upShooterController.setReference(
            speed,
            ControlType.kVelocity,
            0,
            feedforward.calculate(speed));
        downShooterController.setReference(
            speed,
            ControlType.kVelocity,
            0,
            feedforward.calculate(speed));
    }

    private void configShooterMotor(CANSparkMax motor, RelativeEncoder encoder, SparkPIDController controller) {    
        //factory resets the spark max    
        motor.restoreFactoryDefaults();
        //full utilisation on the can loop hell yea
        CANSparkMaxUtil.setCANSparkMaxBusUsage(motor, Usage.kAll);
        //sets current limit
        motor.setSmartCurrentLimit(Constants.SwerveConstants.driveContinuousCurrentLimit);
        //sets inverted or not
        motor.setInverted(Constants.SwerveConstants.driveInvert);
        //sets brake mode or not
        motor.setIdleMode(Constants.SwerveConstants.driveNeutralMode);
        //sets encoder to read velocities as meters per second
        encoder.setVelocityConversionFactor(Constants.SwerveConstants.driveConversionVelocityFactor);
        //sets encoder to read positions as meters traveled
        encoder.setPositionConversionFactor(Constants.SwerveConstants.driveConversionPositionFactor);
        //pid setting fun
        controller.setP(Constants.SwerveConstants.driveKP);
        controller.setI(Constants.SwerveConstants.driveKI);
        controller.setD(Constants.SwerveConstants.driveKD);
        controller.setFF(Constants.SwerveConstants.driveKFF);
        motor.enableVoltageCompensation(Constants.SwerveConstants.voltageComp);
        //burns to spark max
        motor.burnFlash();
        //resets encoder position to 0
        encoder.setPosition(0.0);
    }

    
}
