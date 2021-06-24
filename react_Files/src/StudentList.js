// JavaScript source code
import React, { Component } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import AppNavbar from './AppNavBar';
import { Link } from 'react-router-dom';
import { FileUpload } from 'primereact/fileupload';
class StudentList extends React.Component {

    constructor(props) {
        super(props);
        this.state = {students: []};
        this.remove = this.remove.bind(this);
    }

    componentDidMount() {
        fetch('/students')
            .then(response => response.json())
            .then(data => this.setState({students: data}));
    }
    async remove(id) {
    await fetch(`/students/${id}`, {
        method: 'DELETE',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    }).then(() => {
        let updatedStudents = [...this.state.students].filter(i => i.id !== id);
        this.setState({students: updatedStudents});
    });
    }
    render() {
    const {students, isLoading} = this.state;

    if (isLoading) {
        return <p>Loading...</p>;
    }

    const studentList = students.map(student => {
        return <tr key={student.id}>
            <td>{student.id}</td>
            <td style={{whiteSpace: 'nowrap'}}>{student.name}</td>
            <td>{student.surname}</td>
            <td>{student.phone_number}</td>
            <td>{student.city}</td>
            <td>{student.district}</td>
            <td>{student.description}</td>
            <td>
                <ButtonGroup>
                    <Button size="sm" color="primary" tag={Link} to={"/students/" + student.id}>Edit</Button>
                    <Button size="sm" color="danger" onClick={() => this.remove(student.id)}>Delete</Button>
                    <FileUpload maxFileSize={1000000} multiple={false}  accept=".txt" name="file" url={"./students/upload/" + student.id}></FileUpload>
                </ButtonGroup>
            </td>
        </tr>
    });

    return (
        <div>
            <AppNavbar/>
            <Container fluid>
                <div className="float-right">
                    <Button color="success" tag={Link} to="/students/new">Add Student</Button>
                </div>
                <h3>Students</h3>
                <Table className="mt-4">
                    <thead>
                    <tr>
                        <th width="10%">ID</th>
                        <th width="10%">Name</th>
                        <th width="10%">Surname</th>
                        <th width="10%">Tel</th>
                        <th width="10%">City</th>
                        <th width="10%">District</th>
                        <th width="10%">Description</th>
                        <th width="30%">Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    {studentList}   
                    </tbody>
                </Table>
            </Container>
        </div>
    );
}

}
export default StudentList;