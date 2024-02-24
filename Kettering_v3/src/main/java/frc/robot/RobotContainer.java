package frc.robot;

import com.pathplanner.lib.auto.NamedCommands;
import com.pathplanner.lib.commands.PathPlannerAuto;
import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.path.PathPlannerPath;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.autos.*;
import frc.robot.commands.*;
import frc.robot.subsystems.*;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

    // public static SendableChooser sendableChooser;

    // sendableChooser = new SendableChooser();

    // sendableChooser.addOption("Drive", new AutoDrive(s_Swerve));
    // private final SmartDashboard.putData("Auto", sendableChooser);


    /* Controllers */
    private final Joystick driver = new Joystick(0);
    private final Joystick operator = new Joystick(1);
    Trigger manualIntakeButton = new JoystickButton(operator, 1); // A
    Trigger ampShooterTrigger = new JoystickButton(operator, 2); // B
    //Trigger intakeButton = new JoystickButton(operator, 5);
    //Trigger shooterButton = new JoystickButton(operator, 6);
    Trigger manualShooterTriggerButton = new JoystickButton(operator, 4); // y
    Trigger manualShooterWheelsButtonIn = new JoystickButton(operator, 3); // x
    Trigger climbUpButton = new JoystickButton(operator, 6); // Right Bumper
    Trigger climbDownButton = new JoystickButton(operator, 5); // Left Bumper
    Trigger Fire = new JoystickButton(driver, 2); 
    Trigger IntakeTrigger = new JoystickButton(driver, 1);
    Trigger IntakeOutTrigger = new JoystickButton(driver, 6);
    Trigger LiftUpButton = new JoystickButton(driver, 7);
    Trigger LiftDownButton = new JoystickButton(driver, 30);


    /* Drive Controls */
    private final int translationAxis = 1;
    private final int strafeAxis = 0;
    private final int rotationAxis = 5;

    /* Driver Buttons */
    private final JoystickButton zeroGyro = new JoystickButton(driver, 10);
    private final JoystickButton robotCentric = new JoystickButton(driver, 8);

    /* Subsystems */
    private final Swerve s_Swerve = new Swerve();
    public final Intake_Shooter s_Intake_Shooter = new Intake_Shooter();
    private final Climb s_Climb = new Climb();
    //private final Shooter s_Shooter = new Shooter();
    private final Lift s_Lift = new Lift();
    //auton
    private final SendableChooser<Command> autoChooser;



    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer() {
        s_Swerve.setDefaultCommand(
            new TeleopSwerve(
                s_Swerve, 
                () -> -driver.getRawAxis(translationAxis), 
                () -> -driver.getRawAxis(strafeAxis), 
                () -> -driver.getRawAxis(rotationAxis), 
                () -> robotCentric.getAsBoolean()
            )
        );

        CameraServer.startAutomaticCapture(0);

        manualIntakeButton.onTrue(s_Intake_Shooter.runIntakeCommand());
        manualIntakeButton.onFalse(s_Intake_Shooter.stopIntakeCommand());
        IntakeTrigger.onTrue(s_Intake_Shooter.autoIntake());
        ampShooterTrigger.onTrue(s_Intake_Shooter.runAmpShooterCommand());
        ampShooterTrigger.onFalse(s_Intake_Shooter.stopShooterCommand());
        //intakeButton.onTrue(s_Intake.autoIntake());
        Fire.onTrue(s_Intake_Shooter.AutoShooterCommand2());
        Fire.onFalse(s_Intake_Shooter.stopShooterCommand());
        //Fire.onFalse(s_Intake_Shooter.stopShooterCommand());
        //shooterButton.onTrue(s_Shooter.AutoShooterCommand());
        //shooterButton.onFalse(s_Shooter.stopShooterCommand());
        manualShooterTriggerButton.onTrue(s_Intake_Shooter.runShooterTriggerCommand());
        manualShooterTriggerButton.onFalse(s_Intake_Shooter.stopShooterTriggerCommand());
        climbUpButton.onTrue(s_Climb.ClimbUpCommand());
        climbUpButton.onFalse(s_Climb.stopClimbCommand());
        climbDownButton.onTrue(s_Climb.ClimbDownCommand());
        climbDownButton.onFalse(s_Climb.stopClimbCommand());
        IntakeOutTrigger.onTrue(s_Intake_Shooter.runIntakeOutCommand());
        IntakeOutTrigger.onFalse(s_Intake_Shooter.stopIntakeCommand());
        manualShooterWheelsButtonIn.onTrue(s_Intake_Shooter.runShooterInCOmmand());
        manualShooterWheelsButtonIn.onFalse(s_Intake_Shooter.stopShooterCommand());

        LiftUpButton.onTrue(s_Lift.LiftUpCommand());
        LiftDownButton.onTrue(s_Lift.LiftDownCommand());

        //manualShooterWheelsButton.onTrue(s_Shooter.runShooterCommand());
        //manualShooterWheelsButton.onFalse(s_Shooter.stopShooterCommand());


        // Register Named Commands
        NamedCommands.registerCommand("Intake", s_Intake_Shooter.runIntakeCommand());
        NamedCommands.registerCommand("Shooter", s_Intake_Shooter.AutoShooterCommand2());
        NamedCommands.registerCommand("StopShooter", s_Intake_Shooter.stopShooterCommand());
        //autoChooser
        
        autoChooser = AutoBuilder.buildAutoChooser(); // Default auto will be `Commands.none()'
        //autoChooser.addOption("Test", new PathPlannerAuto("testAuto"));

         //PathPlannerPath path = PathPlannerPath.fromPathFile("exampleAutoPath");
         //PathPlannerPath testPath = PathPlannerPath.fromPathFile("testAuto");
         //PathPlannerAuto testAuto = (PathPlannerAuto) PathPlannerAuto.getPathGroupFromAutoFile("testAuto");
         //autoChooser.addOption("Example", AutoBuilder.followPath(path));
         //autoChooser.addOption("testPath", Swerve.followPathCommand(String testPath));
        //autoChooser.addOption("testPath", Swerve.followPathCommand(String testPath));
        //autoChooser = AutoBuilder.buildAuto(testAuto);
         //Shuffleboard.getTab("Auto Mode").add(autoChooser);
         SmartDashboard.putData("Auto Mode", autoChooser);
        //  SmartDashboard.putNumber("Sensor", input.getValue());
         //Shuffleboard.putData("Shuffle Auto Mode", AutoBuilder.buildAutoChooser());


         configureButtonBindings();
        
    }

    /**
     * Use this method to define your button->command mappings. Buttons can be created by
     * instantiating a {@link GenericHID} or one of its subclasses ({@link
     * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
     * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings() {
        /* Driver Buttons */
        zeroGyro.onTrue(new InstantCommand(() -> s_Swerve.zeroHeading()));
    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        // An ExampleCommand will run in autonomous

        //return new PathPlannerAuto("testAuto");
        return autoChooser.getSelected();
        //Object m_autoSelected;
        //return new AutoDrive(s_Swerve);
        //         // Load the path you want to follow using its name in the GUI
        // PathPlannerPath path = PathPlannerPath.fromPathFile("exampleAutoPath");

        // // Create a path following command using AutoBuilder. This will also trigger event markers.
        // return AutoBuilder.followPathWithEvents(autoChooser.getSelected());
    }
}
