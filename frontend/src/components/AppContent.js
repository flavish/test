import React, {Component} from 'react';
import IssueTable from './IssueTable';
import { findAllIssues } from '../actions/issueActions.js';

class AppContent extends Component {
 state = {
    issues: []
 };

 componentDidMount() {
    findAllIssues((res) => {
        this.setState({
            issues: res.data
        });
    })
 }

 render() {
    const { issues } = this.state;
    return (
        <div>
           <IssueTable issues={issues}/>
        </div>
    );
 }
}

export default AppContent;