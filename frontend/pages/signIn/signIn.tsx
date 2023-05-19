import React, { useState } from 'react';
import { Form, Input, Button } from 'antd';
import { UserOutlined, LockOutlined } from '@ant-design/icons';
import s from './signIn.module.scss';

const SignIn: React.FC = () => {
  const [loading, setLoading] = useState(false);

  const onFinish = (values: any) => {
    setLoading(true);
    // Handle sign in logic
  };

  return (
    <div className={s['sign-in-container']}>
      <h1>Sign In</h1>
      <Form
        name="sign-in-form"
        onFinish={onFinish}
      >
        <Form.Item
          name="email"
          rules={[
            {
              type: 'email',
              message: 'Please enter a valid email address',
            },
            {
              required: true,
              message: 'Please enter your email',
            },
          ]}
        >
          <Input
            prefix={<UserOutlined />}
            placeholder="Email"
          />
        </Form.Item>
        <Form.Item
          name="password"
          rules={[
            {
              required: true,
              message: 'Please enter your password',
            },
          ]}
        >
          <Input.Password
            prefix={<LockOutlined />}
            placeholder="Password"
          />
        </Form.Item>
        <Form.Item>
          <Button
            type="primary"
            htmlType="submit"
            loading={loading}
            className={s['sign-in-button']}
          >
            Sign In
          </Button>
        </Form.Item>
      </Form>
    </div>
  );
};

export default SignIn;
