// package frc.robot.subsystems;

// import edu.wpi.first.wpilibj.DigitalInput;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import edu.wpi.first.wpilibj2.command.Command;
// import edu.wpi.first.wpilibj2.command.SubsystemBase;

// import java.util.function.BooleanSupplier;

// import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
// import com.revrobotics.CANSparkMax;

// public class Shooter extends SubsystemBase{

//     private static CANSparkMax ShooterMotorLeft;
//     private static CANSparkMax ShooterMotorRight;
//     private static WPI_TalonSRX ShooterTrigger;
//     public BooleanSupplier ShooterRPM;
//     //public final Intake s_Intake = new Intake();

//     //private BooleanSupplier NoteValueShooter;
//     // private final DigitalInput NoteInput;


//     public Shooter(){

//         ShooterMotorLeft = new CANSparkMax(15, CANSparkMax.MotorType.kBrushless);
//         ShooterMotorRight = new CANSparkMax(16, CANSparkMax.MotorType.kBrushless);
//         ShooterTrigger = new WPI_TalonSRX(11);
//         //DigitalInput NoteInputShooter = s_Intake.NoteInput; 

//         ShooterTrigger.setInverted(true);
//         ShooterRPM = () -> ShooterMotorLeft.getEncoder().getVelocity() >= 4500;

//         // NoteInput = new DigitalInput(1);
//         //NoteValueShooter = () -> NoteInputShooter.get()==true;
//     }

//     private void runShooter(){
//         ShooterMotorLeft.set(1);
//         ShooterMotorRight.set(1);
//     }

//     private void stopShooter(){
//         ShooterMotorLeft.set(0);
//         ShooterMotorRight.set(0);
//         ShooterTrigger.set(0);
//     }

//     public void runShooterTrigger(){
//         ShooterTrigger.set(1);
//     }

//     public void stopShooterTrigger(){
//         ShooterTrigger.set(0);
//     }

//     public Command runShooterCommand(){
//         return this.runOnce(this::runShooter);
//     }

//     public Command stopShooterCommand(){
//         return this.runOnce(this::stopShooter);
//     }

//     public Command runShooterTriggerCommand(){
//         return this.runOnce(this::runShooterTrigger);
//     }

//     public Command stopShooterTriggerCommand(){
//         return this.runOnce(this::stopShooterTrigger);
//     }

//     // public Command endAutoShooterCommand(){
//     //     return this.startEnd(this::runShooterTrigger, this::stopShooter).until(ShooterRPM);
//     // }

//     public Command AutoShooterCommand(){
//         return this.startEnd(this::runShooter, this::runShooterTrigger).until(ShooterRPM);
//     }

//         @Override
//     public void periodic(){
//         SmartDashboard.putNumber("NEO RPM", ShooterMotorLeft.getEncoder().getVelocity());
//     }

// }
