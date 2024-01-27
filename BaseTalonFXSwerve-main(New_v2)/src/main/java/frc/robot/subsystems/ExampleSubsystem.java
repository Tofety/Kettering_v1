package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;

public class ExampleSubsystem {
    public Command exampleCommand(){
        SmartDashboard.putString("ExampleCommand", "null");
        return null;
    }
}
