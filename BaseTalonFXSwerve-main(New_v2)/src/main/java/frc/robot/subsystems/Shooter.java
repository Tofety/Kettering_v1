package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax; // Import the SparkMax class

public class Shooter extends SubsystemBase{

    private static CANSparkMax ShooterMotorLeft;
    private static CANSparkMax ShooterMotorRight;
    private static WPI_TalonSRX ShooterTrigger;

    public Shooter(){
        ShooterMotorLeft = new CANSparkMax(15, CANSparkMax.MotorType.kBrushless);
        ShooterMotorRight = new CANSparkMax(16, CANSparkMax.MotorType.kBrushless);
        ShooterTrigger = new WPI_TalonSRX(11);
    }

    private void runShooter(){
        ShooterMotorLeft.set(0.8);
        ShooterMotorRight.set(0.8);
    }

    public Command runShooterCommand(){
        return this.runOnce(this::runShooter);
    }


}
