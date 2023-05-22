import React, { useEffect, useState } from "react";
import s from "./signUp.module.scss";

import Link from "next/link";
import { Form, Input } from "antd";
import { UserOutlined, LockOutlined, MailOutlined } from "@ant-design/icons";

import MyButton from "../../components/MUI/MyButton/MyButton";
import { GoogleLogin } from "@react-oauth/google";
import axios from "axios";

const SignUp: React.FC = () => {
  // Данные пользователя для регистрации
  const [userRegister, setUserRegister] = useState({
    fullName: "test",
    email: "test@gmail.com",
    password: "123456",
  });

  console.log(userRegister);

  const onFinish = () => {
    setLoading(true);
  };

  const [loading, setLoading] = useState(false);

  const handleSubmit = async () => {
    const BASE_URL = "http://localhost:8080/auth/register";
    try {
      const response = await axios.post(BASE_URL, userRegister);
      console.log(response);

      setUserRegister({
        fullName: "",
        email: "",
        password: "",
      });
    } catch (error) {
      console.log(error);
    }
  };

  useEffect(() => {
    handleSubmit()
  },[])

  return (
    <div className={s.signUp}>
      <h1>Register</h1>
      <Form name="sign-up-form" onFinish={onFinish} onSubmit={handleSubmit}>
        <Form.Item
          name="name"
          rules={[
            {
              required: true,
              message: "Please enter your name",
            },
          ]}
        >
          <Input
            value={userRegister.fullName}
            onChange={(e) => {
              setUserRegister({ ...userRegister, fullName: e.target.value });
            }}
            prefix={<UserOutlined />}
            placeholder="Name"
          />
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
          <Input
            value={userRegister.email}
            onChange={(e) => {
              setUserRegister({ ...userRegister, email: e.target.value });
            }}
            prefix={<MailOutlined />}
            placeholder="Email"
          />
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
          <Input.Password
            value={userRegister.password}
            onChange={(e) => {
              setUserRegister({ ...userRegister, password: e.target.value });
            }}
            prefix={<LockOutlined />}
            placeholder="Password"
          />
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
            value={userRegister.password}
            onChange={(e) => {
              setUserRegister({ ...userRegister, password: e.target.value });
            }}
            prefix={<LockOutlined />}
            placeholder="Confirm Password"
          />
        </Form.Item>

        <Form.Item>
          <MyButton
            background="#7329c2"
            hoverBackground="#03d665"
            type="primary"
            loading={loading}
            
          >
            Sign Up
          </MyButton>
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

        <Form.Item>
          <Link href="/paymentPage/paymentPage">Payment page</Link>
        </Form.Item>

        <Form.Item>
          <Link href="/passwordRecovery/passwordRecovery">
            Восстановления пароля
          </Link>
        </Form.Item>
      </Form>
    </div>
  );
};

export default SignUp;
