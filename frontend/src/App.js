import React from 'react';
import { Layout } from 'antd';
import AppContent from './components/AppContent';
import styled from 'styled-components';

import 'antd/es/layout/style/index.css';

const { Header, Content } = Layout;

const HeaderStyled = styled(Header)`
    color: white;
`;

const App = (props) => (
    <Layout>
        <HeaderStyled>Задачи</HeaderStyled>
        <Content><AppContent/></Content>
    </Layout>
);

export default App;