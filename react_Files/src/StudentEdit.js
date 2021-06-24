import React, { Component } from 'react';
import { Link, withRouter } from 'react-router-dom';
import { Button, Container, Form, FormGroup, Input, Label } from 'reactstrap';
import AppNavbar from './AppNavBar';
import Select from 'react-select-me';
import 'react-select-me/lib/ReactSelectMe.css';
import InputMask from 'react-input-mask';
class StudentEdit extends React.Component {
    emptyItem = {
        id: '',
        name: '',
        surname: '',
        phone_number: '',
        city: '',
        district: '',
        description: ''
    };

    constructor(props) {
        super(props);
        this.state = {
            item: this.emptyItem
        };
        this.handleChange = this.handleChange.bind(this);
        this.handleSelectCityChange = this.handleSelectCityChange.bind(this);
        this.handleSelectDistrictChange = this.handleSelectDistrictChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.city_district = null;
        this.cities = [];
        this.districts = [];
    }
    async componentDidMount(){
        this.city_district = await (await fetch('/students/city_district_data')).json();
        for(let i = 0 ; i < this.city_district.length; ++i){
            if(this.city_district[i].length > 0){
                this.cities.push({
                id: i,
                value: this.city_district[i][0],
                label: this.city_district[i][0]
				}); 
            }
		}
        if (this.props.match.params.id !== 'new') {
            const student = await (await fetch(`/students/${this.props.match.params.id}`)).json();
            this.setState({item: student});
        }
    }
    handleChange(event) {
        console.log("change is happening");
        const target = event.target;
        const value = target.value;
        const name = target.name;

        let item = {...this.state.item};
        item[name] = value;
        this.setState({item});
        this.setState({item});
    }
    handleSelectCityChange(arr){
    console.log("city change.");
        const value = arr.value;
        const name = 'city';

        let item = {...this.state.item};
        item[name] = value;
        this.setState({item});
        this.setState({item});

        this.districts = [];
        for(let i = 1 ; i < this.city_district[arr.id].length; ++i){
            this.districts.push({
                value: this.city_district[arr.id][i],
                label: this.city_district[arr.id][i]
			});
		}
	}
    handleSelectDistrictChange(arr){
    console.log("district change.");
        const value = arr.value;
        const name = 'district';

        let item = {...this.state.item};
        item[name] = value;
        this.setState({item});
        this.setState({item});
    }
    async handleSubmit(event) {
        event.preventDefault();
        const {item} = this.state;
        await fetch('/students' + (item.id ? '/' + item.id : ''), {
            method: (item.id) ? 'PUT' : 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(item),
        });
        this.props.history.push('/students');
    }
    render() {
    const {item} = this.state;
    const title = <h2>{item.id ? 'Edit Student' : 'Add Student'}</h2>;

    return <div>
        <AppNavbar/>
        <Container>
            {title}
            <Form onSubmit={this.handleSubmit}>
                <FormGroup>
                    <Label for="name">Name</Label>
                    <Input maxlength="50" required pattern="[^0-9]*" type="text" name="name" id="name" value={item.name || ''}
                           onChange={this.handleChange} autoComplete="name"/>
                </FormGroup>
                <FormGroup>
                    <Label for="surname">Surname</Label>
                    <Input maxlength="50" required pattern="[^0-9]*" type="text" name="surname" id="surname" value={item.surname || ''}
                           onChange={this.handleChange} autoComplete="surname"/>
                </FormGroup>
                <FormGroup>
                    <Label for="city">City</Label>
                    <Select required type="text" options={this.cities}  name="city" id="city" value={item.city} onChange={this.handleSelectCityChange} autoComplete="city"/>
                </FormGroup>
                <FormGroup id="dynamicDistrict">
                    <Label for="district">District</Label>
                    <Select required type="text" options={this.districts}  name="district" id="district" value={item.district} onChange={this.handleSelectDistrictChange} autoComplete="district"/>
                </FormGroup>
                <FormGroup>
                    <Label maxlength="500" for="description">Description</Label>
                    <Input required  type="text" name="description" id="description" value={item.description || ''}
                           onChange={this.handleChange} autoComplete="description"/>
                </FormGroup>
                <FormGroup>
                    <Label for="phone_number">Phone (example: 5061238457)</Label>
                    <InputMask required autoComplete="phone_number" onChange={this.handleChange} value={item.phone_number || ''} mask="9999999999" name="phone_number" id="phone_number" type="tel" /> 

                </FormGroup>
                <FormGroup>
                    <Button color="primary" type="submit">Save</Button>{' '}
                    <Button color="secondary" tag={Link} to="/students">Cancel</Button>
                </FormGroup>
            </Form>
        </Container>
    </div>

    }
}
export default withRouter(StudentEdit);
