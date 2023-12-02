import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

class Student implements Serializable {
    private String name;
    private int rollNumber;
    private String grade;

    public Student(String name, int rollNumber, String grade) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public String getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", rollNumber=" + rollNumber +
                ", grade='" + grade + '\'' +
                '}';
    }
}

class StudentManagementSystem implements Serializable {
    private List<Student> students;

    public StudentManagementSystem() {
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public List<Student> getAllStudents() {
        return new ArrayList<>(students);
    }
}

public class StudentManagementSystemGUI extends JFrame {

    private StudentManagementSystem system;

    private JTextField nameField;
    private JTextField rollNumberField;
    private JTextField gradeField;
    private JTextArea resultArea;

    public StudentManagementSystemGUI() {
        this.system = new StudentManagementSystem();

        setTitle("Student Management System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initializeComponents();
    }

    private void initializeComponents() {
        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField(20);

        JLabel rollNumberLabel = new JLabel("Roll Number:");
        rollNumberField = new JTextField(10);

        JLabel gradeLabel = new JLabel("Grade:");
        gradeField = new JTextField(10);

        JButton addButton = new JButton("Add Student");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addStudent();
            }
        });

        JButton displayButton = new JButton("Display Students");
        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayStudents();
            }
        });

        resultArea = new JTextArea();
        resultArea.setEditable(false);

        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(rollNumberLabel);
        panel.add(rollNumberField);
        panel.add(gradeLabel);
        panel.add(gradeField);
        panel.add(addButton);
        panel.add(displayButton);

        setLayout(new BorderLayout());
        add(panel, BorderLayout.NORTH);
        add(new JScrollPane(resultArea), BorderLayout.CENTER);
    }

    private void addStudent() {
        try {
            String name = nameField.getText();
            int rollNumber = Integer.parseInt(rollNumberField.getText());
            String grade = gradeField.getText();

            Student student = new Student(name, rollNumber, grade);
            system.addStudent(student);

            resultArea.setText("Student added: " + student);
        } catch (NumberFormatException ex) {
            resultArea.setText("Invalid input. Please enter valid numbers.");
        }
    }

    private void displayStudents() {
        resultArea.setText("");
        for (Student student : system.getAllStudents()) {
            resultArea.append(student.toString() + "\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new StudentManagementSystemGUI().setVisible(true);
            }
        });
    }
}
