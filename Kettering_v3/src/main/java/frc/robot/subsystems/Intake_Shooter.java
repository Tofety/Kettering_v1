package frc.robot.subsystems;

import java.util.function.BooleanSupplier;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix6.hardware.TalonFX;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.SwerveModule;

public class Intake_Shooter extends SubsystemBase {
    // intake
    private static TalonSRX TIntake;
    private static TalonSRX BIntake;
    //private final AnalogInput NoteInput; 
    private BooleanSupplier NoteValue;
    public final DigitalInput NoteInput;

    //Shooter
    private static CANSparkMax ShooterMotorLeft;
    private static CANSparkMax ShooterMotorRight;
    private static WPI_TalonSRX ShooterTrigger;
    public BooleanSupplier ShooterRPM;
    private BooleanSupplier NoteValueShooter;

    
    public Intake_Shooter() {
        //Intake
        TIntake = new TalonSRX(12);
        BIntake = new TalonSRX(10);
        //NoteInput = new AnalogInput(0);
        NoteInput = new DigitalInput(1);
        NoteValue = () -> NoteInput.get()==false;

        //Shooter
        ShooterMotorLeft = new CANSparkMax(15, CANSparkMax.MotorType.kBrushless);
        ShooterMotorRight = new CANSparkMax(16, CANSparkMax.MotorType.kBrushless);
        ShooterTrigger = new WPI_TalonSRX(11);
        //DigitalInput NoteInputShooter = s_Intake.NoteInput; 

        ShooterTrigger.setInverted(true);
        ShooterRPM = () -> ShooterMotorLeft.getEncoder().getVelocity() >= 4150;

        // NoteInput = new DigitalInput(1);
        NoteValueShooter = () -> NoteInput.get()==true;
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
        TIntake.set(ControlMode.PercentOutput, 0.75);
        BIntake.set(ControlMode.PercentOutput, 0.75);
    }

    private void stopIntake(){
        TIntake.set(ControlMode.PercentOutput, 0);
        BIntake.set(ControlMode.PercentOutput, 0); 
        ShooterTrigger.set(0);
    }

    private void runIntakeOut(){
        TIntake.set(ControlMode.PercentOutput, -1);
        BIntake.set(ControlMode.PercentOutput, -1);
        ShooterTrigger.set(-1);
    }

    public Command runIntakeCommand(){
       return this.runOnce(this::runIntake);   
    }

    public Command runIntakeOutCommand(){
        return this.runOnce(this::runIntakeOut);
    }

    public Command stopIntakeCommand(){
        return this.runOnce(this::stopIntake);
    }

    public Command autoIntake(){
        return this.startEnd(this::runIntake, this::stopIntake).until(NoteValue);
    }

    @Override
    public void periodic(){
        SmartDashboard.putBoolean("NOTE IN/OUT (red - IN, green - OUT)", SensorValue());
        SmartDashboard.putBoolean("New Sensor", NewSensorValue());
        SmartDashboard.putNumber("NEO RPM", ShooterMotorLeft.getEncoder().getVelocity());
    }

    private void runShooter(){
        ShooterMotorLeft.set(0.85);
        ShooterMotorRight.set(0.85);
    }

    private void stopShooter(){
        ShooterMotorLeft.set(0);
        ShooterMotorRight.set(0);
        ShooterTrigger.set(0);
    }

    public void runShooterTrigger(){
        ShooterTrigger.set(1);
    }

    public void stopShooterTrigger(){
        ShooterTrigger.set(0);
    }

    public void runAmpShooter(){
        ShooterMotorLeft.set(0.5);
        ShooterMotorRight.set(0.5);
        ShooterTrigger.set(1);
    }

    public void runShooterIn(){
        ShooterMotorLeft.set(-0.8);
        ShooterMotorRight.set(-0.8);
    }

    public Command runAmpShooterCommand(){
        return this.runOnce(this::runAmpShooter);
    }

    public Command runShooterCommand(){
        return this.runOnce(this::runShooter);
    }

    public Command stopShooterCommand(){
        return this.runOnce(this::stopShooter);
    }

    public Command runShooterTriggerCommand(){
        return this.runOnce(this::runShooterTrigger);
    }

    public Command stopShooterTriggerCommand(){
        return this.runOnce(this::stopShooterTrigger);
    }

    
    public Command AutoShooterCommand(){
        return this.startEnd(this::runShooter, this::runShooterTrigger).until(ShooterRPM);
    }

    public Command runShooterInCOmmand(){
        return this.runOnce(this::runShooterIn);
    }

    // public Command NewAutoShooterCommand(){
    //     //return this.startEnd(this::runShooter, this::runShooterTrigger).until(ShooterRPM);
    //     return this.runOnce(this::AutoShooterCommand).until(NoteValueShooter);
    // }

    public Command AutoShooterCommand2() {
        // return AutoShooterCommand().andThen(Commands.waitSeconds(1)).andThen(stopShooterCommand());
        // return this.startEnd(this::AutoShooterCommand, this::stopShooterCommand).until(NoteValueShooter);
        return AutoShooterCommand().andThen(Commands.waitSeconds(2)).andThen(stopShooterCommand());
    }


    // stop intake
    // reverse intake

    // set up commands
    //   public Command runIntakeCommand() {
    //     // implicitly require `this`
    //     return this.runOnce(() -> motor.setSpeed(1));
    // }
    
}
