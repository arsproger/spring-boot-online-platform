import React, { useState } from "react";
import { Form, Input, Button } from "antd";
import { UserOutlined, LockOutlined } from "@ant-design/icons";
import s from "./signIn.module.scss";
import MyButton from "@/components/MUI/MyButton/MyButton";
import Link from "next/link";
import axios from "axios";
import { useRouter } from "next/router";

const SignIn: React.FC = () => {
  // Данные пользователя для регистрации
  const [userLogin, setUserLogin] = useState({
    username: "",
    password: "",
  });
  const [loading, setLoading] = useState(false);

  const onFinish = () => {
    setLoading(true);
  };
  const router = useRouter();

  const handleSubmit = async () => {
    const BASE_URL = "http://localhost:8080";
    try {
      const response = await axios.post(BASE_URL + "/auth/login", userLogin);

      // Достаем токен пользовотеля
      const token = JSON.parse(localStorage.getItem("token"));

      if (!!token) {
        router.push("/profile/profile");
      }

      console.log(response);

      setUserLogin({
        username: "",
        password: "",
      });
    } catch (error) {
      console.log(error);
    }
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
          <Input
            defaultValue={userLogin.username}
            onChange={(e) => {
              setUserLogin({ ...userLogin, username: e.target.value });
            }}
            prefix={<UserOutlined />}
            placeholder="Email"
          />
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
          <Input.Password
            defaultValue={userLogin.password}
            onChange={(e) => {
              setUserLogin({ ...userLogin, password: e.target.value });
            }}
            prefix={<LockOutlined />}
            placeholder="Password"
          />
        </Form.Item>

        <Form.Item>
          <MyButton
            background="#03d665"
            hoverBackground="#7329c2"
            type="primary"
            loading={loading}
            onClick={handleSubmit}
          >
            Sign In
          </MyButton>
        </Form.Item>

        <Form.Item>
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

export default SignIn;
