package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Lift extends SubsystemBase {

    public static Solenoid Left;
    public static Solenoid Right;
    public static Compressor compressor;
    public Lift(){

    compressor = new Compressor(PneumaticsModuleType.REVPH);
    Left = new Solenoid(PneumaticsModuleType.REVPH, 0);
    Right = new Solenoid(PneumaticsModuleType.REVPH, 1);
    }
    
    public void LiftUp(){
        Left.set(true);
        Right.set(true);
    }

    public void LiftDown(){
        Left.set(false);
        Right.set(false);
    }

    public Command LiftUpCommand(){
        return this.runOnce(this::LiftUp);
    }

    public Command LiftDownCommand(){
        return this.runOnce(this::LiftDown);
    }

        @Override
    public void periodic(){
        SmartDashboard.putBoolean("Left Solenoid", Left.get());
        SmartDashboard.putBoolean("Right Solenoid", Right.get());
    }
}
