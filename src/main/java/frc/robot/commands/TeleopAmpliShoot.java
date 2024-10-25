package frc.robot.commands;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.AmpliShoot;
import edu.wpi.first.wpilibj.Timer;

public class TeleopAmpliShoot extends Command{
    private AmpliShoot ampliShoot;
    private BooleanSupplier shoot;
    private int ampliStatus = 10;
    private double incio_ampli = 0;
    public TeleopAmpliShoot(AmpliShoot ampliShoot, BooleanSupplier shoot) {
        this.ampliShoot = ampliShoot;
        addRequirements(ampliShoot);
        this.shoot = shoot; 
    }
    @Override
    public void initialize() {
        

    }
    @Override
    public void execute() {
        if(shoot.getAsBoolean()){
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
                ampliShoot.runShooter(0.7);
                ampliShoot.feederRun(0.5);
                if((Timer.getFPGATimestamp()- incio_ampli)> 2) ampliStatus = 4;
            }
            if(ampliStatus == 4){
                ampliShoot.runShooter(0);
                ampliShoot.feederRun(0);
                if((Timer.getFPGATimestamp()- incio_ampli)> 2) ampliStatus = 10;
            }
        }
    }
    @Override
    public void end(boolean interrupted) {
    }
    @Override
    public boolean isFinished() {
        return false;
    }

    
    
}
