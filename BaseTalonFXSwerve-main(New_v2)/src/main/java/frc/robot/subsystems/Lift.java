package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Lift extends SubsystemBase {

    public static Solenoid Left;
    public static Solenoid Right;
    public static Compressor compressor;
    public Lift(){

    compressor = new Compressor(PneumaticsModuleType.REVPH);
    Left = new Solenoid(PneumaticsModuleType.CTREPCM, 0);
    Right = new Solenoid(PneumaticsModuleType.CTREPCM, 1);
    }
    
}
