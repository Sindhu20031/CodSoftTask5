import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

class Student {
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
}

class StudentManagementSystem {
    private List<Student> students;

    public StudentManagementSystem() {
        students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(Student student) {
        students.remove(student);
    }

    public Student searchStudent(String name) {
        for (Student student : students) {
            if (student.getName().equals(name)) {
                return student;
            }
        }
        return null;
    }

    public List<Student> getAllStudents() {
        return students;
    }
}

public class StudentManagementUI extends JFrame implements ActionListener {
    private StudentManagementSystem system;
    private JTextField nameField, rollNumberField, gradeField;
    private JButton addButton, removeButton, searchButton, displayButton;

    public StudentManagementUI() {
        system = new StudentManagementSystem();
        setTitle("Student Management System");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        nameField = new JTextField(20);
        rollNumberField = new JTextField(10);
        gradeField = new JTextField(5);
        addButton = new JButton("Add Student");
        removeButton = new JButton("Remove Student");
        searchButton = new JButton("Search Student");
        displayButton = new JButton("Display All Students");

        addButton.addActionListener(this);
        removeButton.addActionListener(this);
        searchButton.addActionListener(this);
        displayButton.addActionListener(this);

        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Roll Number:"));
        panel.add(rollNumberField);
        panel.add(new JLabel("Grade:"));
        panel.add(gradeField);
        panel.add(addButton);
        panel.add(removeButton);
        panel.add(searchButton);
        panel.add(displayButton);

        add(panel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            String name = nameField.getText();
            int rollNumber = Integer.parseInt(rollNumberField.getText());
            String grade = gradeField.getText();
            system.addStudent(new Student(name, rollNumber, grade));
            JOptionPane.showMessageDialog(this, "Student added successfully.");
            clearFields(); // Clear input fields after adding a student
        } else if (e.getSource() == removeButton) {
            String name = nameField.getText();
            Student student = system.searchStudent(name);
            if (student != null) {
                system.removeStudent(student);
                JOptionPane.showMessageDialog(this, "Student removed successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "Student not found.");
            }
            clearFields(); // Clear input fields after removing a student
        } else if (e.getSource() == searchButton) {
            String name = nameField.getText();
            Student student = system.searchStudent(name);
            if (student != null) {
                JOptionPane.showMessageDialog(this, "Student found:\nName: " + student.getName()
                        + "\nRoll Number: " + student.getRollNumber() + "\nGrade: " + student.getGrade());
            } else {
                JOptionPane.showMessageDialog(this, "Student not found.");
            }
        } else if (e.getSource() == displayButton) {
            StringBuilder message = new StringBuilder("All Students:\n");
            for (Student student : system.getAllStudents()) {
                message.append("Name: ").append(student.getName())
                        .append(", Roll Number: ").append(student.getRollNumber())
                        .append(", Grade: ").append(student.getGrade()).append("\n");
            }
            JOptionPane.showMessageDialog(this, message.toString());
        }
    }

    private void clearFields() {
        nameField.setText("");
        rollNumberField.setText("");
        gradeField.setText("");
    }

    public static void main(String[] args) {
        new StudentManagementUI();
    }
}
