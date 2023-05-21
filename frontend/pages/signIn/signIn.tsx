import React, { useState } from "react";
import { Form, Input, Button } from "antd";
import { UserOutlined, LockOutlined } from "@ant-design/icons";
import s from "./signIn.module.scss";
import MyButton from "@/components/MUI/MyButton/MyButton";
import { GoogleLogin } from "@react-oauth/google";

const SignIn: React.FC = () => {
  const [loading, setLoading] = useState(false);

  const onFinish = () => {
    setLoading(true);
  };

  return (
    <div className={s.signIn}>
      <h1>Login</h1>
      <Form name="sign-in-form" onFinish={onFinish}>
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
          <Input prefix={<UserOutlined />} placeholder="Email" />
        </Form.Item>

        <Form.Item
          name="password"
          rules={[
            {
              required: true,
              message: "Please enter your password",
            },
          ]}
        >
          <Input.Password prefix={<LockOutlined />} placeholder="Password" />
        </Form.Item>

        <Form.Item>
          <MyButton loading={loading}>Sign In</MyButton>
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

export default SignIn;
