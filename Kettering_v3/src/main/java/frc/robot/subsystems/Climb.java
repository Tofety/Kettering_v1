package frc.robot.subsystems;

import java.util.function.BooleanSupplier;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climb extends SubsystemBase{
    private static DigitalInput lowerClimbLimit;
    private static DigitalInput upperClimbLimit;
    private static TalonSRX climbMotor;
    private static BooleanSupplier upperLimit;
    private static BooleanSupplier lowerLimit;
    public Climb(){
        lowerClimbLimit = new DigitalInput(2);
        upperClimbLimit = new DigitalInput(3);
        climbMotor = new TalonSRX(13);
        climbMotor.setInverted(true);
        upperLimit = () -> upperClimbLimit.get() == true;
        lowerLimit = () -> lowerClimbLimit.get() == true;
    }

    public void runUpClimb(){
        climbMotor.set(ControlMode.PercentOutput, 1);
    }

    public void runDownClimb(){
        climbMotor.set(ControlMode.PercentOutput, -1);
    }

    public void stopClimb(){
        climbMotor.set(ControlMode.PercentOutput, 0);
    }

    public Command runUpClimbCommand(){
        return this.runOnce(this::runUpClimb);
    }

    public Command runDownClimbCommand(){
        return this.runOnce(this::runDownClimb);
    }

    public Command stopClimbCommand(){
        return this.runOnce(this::stopClimb);
    }

    public Command ClimbUpCommand(){
        return this.startEnd(this::runUpClimb, this::stopClimb).until(upperLimit);
    }

    public Command ClimbDownCommand(){
        return this.startEnd(this::runDownClimb, this::stopClimb).until(lowerLimit);
    }

    @Override
    public void periodic(){
        SmartDashboard.putBoolean("Lower Limit Switch", lowerClimbLimit.get());
        SmartDashboard.putBoolean("Upper Limit Switch", upperClimbLimit.get());
    }
}