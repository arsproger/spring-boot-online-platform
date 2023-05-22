import React, { useState } from "react";
import s from "./signUp.module.scss";

import Link from "next/link";
import { Form, Input } from "antd";
import { UserOutlined, LockOutlined, MailOutlined } from "@ant-design/icons";

import MyButton from "../../components/MUI/MyButton/MyButton";
import axios from "axios";
import { useRouter } from "next/router";

const SignUp: React.FC = () => {
  //  Состояния - для данных пользователя регистрации
  const [userRegister, setUserRegister] = useState({
    fullName: "",
    email: "",
    password: "",
  });

  // Состояния - для загрузки кнопки
  const [loading, setLoading] = useState(false);

  // Функция - для загрузки кнопки
  const onFinish = () => {
    setLoading(!loading);
  };

  // Для - маршутизации
  const router = useRouter();

  // Функция - для отправки формы
  const handleSubmit = async () => {
    const BASE_URL = "http://localhost:8080";
    try {
      const response = await axios.post(
        BASE_URL + "/auth/register",
        userRegister
      );
      // Сохраняем токен пользователя
      localStorage.setItem("token", JSON.stringify(response.data.token));

      // Достаем токен пользовотеля
      const token = JSON.parse(localStorage.getItem("token"));

      if (!!token) {
        router.push("/profile/profile");
      }
      setUserRegister({
        fullName: "",
        email: "",
        password: "",
      });
    } catch (error) {
      console.log(error);
    }
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
          <Input
            defaultValue={userRegister.fullName}
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
            defaultValue={userRegister.email}
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
            defaultValue={userRegister.password}
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
            defaultValue={userRegister.password}
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
            onClick={handleSubmit}
          >
            Sign Up
          </MyButton>
        </Form.Item>

        <Form.Item>
          <a
            href="http://localhost:8080/oauth2/authorization/google"
            target="_blank"
          >
            Войдите в аккаунт Google
          </a>
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
