package frc.robot.commands;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.AmpliShoot;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj.Timer;

public class TeleopAmpliShoot extends Command{
    private AmpliShoot ampliShoot;
    private Intake intake;
    private BooleanSupplier shoot;
    private int ampliStatus = 10;
    private double incio_ampli = 0;
    public TeleopAmpliShoot(AmpliShoot ampliShoot, Intake intake, BooleanSupplier shoot) {
        this.ampliShoot = ampliShoot;
        this.intake = intake;
        addRequirements(ampliShoot, intake);
        this.shoot = shoot; 
    }
    @Override
    public void initialize() {
        

    }
    @Override
    public void execute() {
        if(shoot.getAsBoolean()){
            System.out.println("Shoot activated");
            this.ampliStatus = 0;
            this.incio_ampli = Timer.getFPGATimestamp();
            
        }
        ampliShoot();
    }
    public void ampliShoot() {
        if(ampliStatus != 10){
            if(ampliStatus == 0){
                ampliShoot.runShooter(0.1);
                if((Timer.getFPGATimestamp()- incio_ampli)> 0.5) ampliStatus = 1;
            }
            if(ampliStatus == 1){
                ampliShoot.runShooter(0.3);
                if((Timer.getFPGATimestamp()- incio_ampli)> 0.5) ampliStatus = 2;
            }
            if(ampliStatus == 2){
                ampliShoot.runShooter(0.5);
                if((Timer.getFPGATimestamp()- incio_ampli)> 1.5) ampliStatus = 3;
            }
            if(ampliStatus == 3){
                ampliShoot.runShooter(0.8);
                intake.feederRun(0.5);
                if((Timer.getFPGATimestamp()- incio_ampli)> 2.2) ampliStatus = 4;
            }
            if(ampliStatus == 4){
                ampliShoot.runShooter(0);
                intake.feederRun(0);
                if((Timer.getFPGATimestamp()- incio_ampli)> 2.2) ampliStatus = 10;
            }
        }
    }
    @Override
    public void end(boolean interrupted) {
        ampliShoot.runShooter(0);
        intake.feederRun(0);
        ampliStatus = 10;
        // System.out.println("Shoot interrupted");
    }
    @Override
    public boolean isFinished() {
        // if (ampliStatus == 10) return true;
        return false;
    }

    
    
}
