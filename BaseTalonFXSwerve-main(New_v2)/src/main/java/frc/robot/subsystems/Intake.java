package frc.robot.subsystems;

import java.util.function.BooleanSupplier;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.SwerveModule;

public class Intake extends SubsystemBase {
    // intake motor declaration
    private static TalonSRX TIntake;
    private static TalonSRX BIntake;
    //private final AnalogInput NoteInput; 
    private BooleanSupplier NoteValue;
    public final DigitalInput NoteInput;
    
    //Photosensor
    
    // constructor for class
    public Intake() {
        TIntake = new TalonSRX(12);
        BIntake = new TalonSRX(10);
        //NoteInput = new AnalogInput(0);
        NoteInput = new DigitalInput(1);
        NoteValue = () -> NoteInput.get()==false;
    }

    // private boolean isNoteThere () {
    //     return NoteInput.getValue() >= 1000;
    // }


    // control functions
    // run intake

    public boolean SensorValue(){
        return NoteInput.get();
    }

    public boolean NewSensorValue(){
        return NoteInput.get();
    }

    private void runIntake() {
        TIntake.set(ControlMode.PercentOutput, 0.5);
        BIntake.set(ControlMode.PercentOutput, 0.5);
    }

    private void stopIntake(){
        TIntake.set(ControlMode.PercentOutput, 0);
        BIntake.set(ControlMode.PercentOutput, 0); 
    }

    public Command runIntakeCommand(){
       return this.runOnce(this::runIntake);   
    }

    public Command stopIntakeCommand(){
        return this.runOnce(this::stopIntake);
    }

    public Command autoIntake(){
        return this.startEnd(this::runIntake, this::stopIntake).until(NoteValue);
    }

    @Override
    public void periodic(){
        SmartDashboard.putBoolean("Sensor", SensorValue());
        SmartDashboard.putBoolean("New Sensor", NewSensorValue());
    }
    // stop intake
    // reverse intake

    // set up commands
    //   public Command runIntakeCommand() {
    //     // implicitly require `this`
    //     return this.runOnce(() -> motor.setSpeed(1));
    // }
    
}
