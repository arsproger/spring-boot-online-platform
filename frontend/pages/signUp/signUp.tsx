import React, { useState } from "react";
import s from "./signUp.module.scss";

import { Form, Input } from "antd";
import {
  UserOutlined,
  LockOutlined,
  MailOutlined,
  GoogleOutlined,
  GithubOutlined,
} from "@ant-design/icons";

import MyButton from "../../components/MUI/MyButton/MyButton";
import { GoogleLogin } from "@react-oauth/google";

const SignUp: React.FC = () => {
  const [loading, setLoading] = useState(false);

  const onFinish = () => {
    setLoading(true);
  };

  return (
    <div className={s.signUp}>
      <h1>Register</h1>
      <Form name="sign-up-form" onFinish={onFinish}>
        <Form.Item
          name="name"
          rules={[
            {
              required: true,
              message: "Please enter your name",
            },
          ]}
        >
          <Input prefix={<UserOutlined />} placeholder="Name" />
        </Form.Item>

        <Form.Item
          name="email"
          rules={[
            {
              type: "email",
              message: "Please enter a valid email address",
            },
            {
              required: true,
              message: "Please enter your email",
            },
          ]}
        >
          <Input prefix={<MailOutlined />} placeholder="Email" />
        </Form.Item>

        <Form.Item
          name="password"
          rules={[
            {
              min: 6,
              message: "Password must be at least 6 characters long",
            },
            {
              required: true,
              message: "Please enter your password",
            },
          ]}
        >
          <Input.Password prefix={<LockOutlined />} placeholder="Password" />
        </Form.Item>

        <Form.Item
          name="confirmPassword"
          dependencies={["password"]}
          rules={[
            {
              required: true,
              message: "Please confirm your password",
            },
            ({ getFieldValue }) => ({
              validator(_, value) {
                if (!value || getFieldValue("password") === value) {
                  return Promise.resolve();
                }
                return Promise.reject(new Error("Passwords do not match"));
              },
            }),
          ]}
        >
          <Input.Password
            prefix={<LockOutlined />}
            placeholder="Confirm Password"
          />
        </Form.Item>

        <Form.Item>
          <MyButton loading={loading}>Sign Up</MyButton>
        </Form.Item>

        <Form.Item>
          <GoogleLogin
            onSuccess={(credentialResponse) => {
              console.log(credentialResponse);
            }}
            onError={() => {
              console.log("Login Failed");
            }}
          />
        </Form.Item>
      </Form>
    </div>
  );
};

export default SignUp;
