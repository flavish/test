import React, {Component} from 'react';
import { Table } from 'antd';

import 'antd/es/table/style/index.css';

class IssueTable extends Component {
 createColumns = () => {
    return [
      {
        title: 'Наименование проекта',
        dataIndex: '',
        key: 'projectKey',
        render: r => <span>{r.project.key}</span>
      },
      {
        title: 'Наименование задачи',
        dataIndex: 'key',
        key: 'key',
      },
      {
        title: 'Описание задачи',
        dataIndex: 'description',
        key: 'description',
      },
    ];
 }

 render() {
    const { issues } = this.props;
    return (
        <Table
            columns={this.createColumns()}
            dataSource={issues}
            pagination={false}
        />
    );
 };
}

export default IssueTable;