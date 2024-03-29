import React, {Component} from 'react';
import IssueTable from './IssueTable';
import Upload from 'antd/lib/upload';
import Button from 'antd/lib/button';
import Icon from 'antd/lib/icon';
import { notification } from 'antd';
import { findAllIssues } from '../actions/issueActions.js';
import { importIssues } from '../actions/importActions.js';
import styled from 'styled-components';

import 'antd/es/notification/style/index.css';
import 'antd/es/upload/style/index.css';
import 'antd/es/button/style/index.css';
import 'antd/es/icon/style/index.css';

const UploadStyled = styled(Upload)`
    margin: 5px;
`;

class AppContent extends Component {
 state = {
    issues: []
 };

 loadIssues = () => {
    findAllIssues((res) => {
        this.setState({
            issues: res.data
        });
    }, this.showErrorNotification);
 }

 componentDidMount() {
    this.loadIssues();
 }

 showErrorNotification = (error) => {
     notification.error({
        message: 'Ошибка',
        description: error.response.data
     });
 }

 importIssuesAction = (options) => {
    importIssues(options.file, this.loadIssues, this.showErrorNotification)
 }

 render() {
    const { issues } = this.state;
    return (
        <div>
           <UploadStyled customRequest={this.importIssuesAction}>
             <Button>
               <Icon type="upload" /> Импорт задач
             </Button>
           </UploadStyled>
           <IssueTable issues={issues}/>
        </div>
    );
 }
}

export default AppContent;