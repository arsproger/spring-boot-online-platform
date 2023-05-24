import React, { FC, useEffect, useState } from "react";
import s from "./signUp.module.scss";

import { useRouter } from "next/router";
import axios, { AxiosResponse } from "axios";
import { Form, Input } from "antd";
import { UserOutlined, LockOutlined, MailOutlined } from "@ant-design/icons";

import en from "../../locales/EN/translation.json";
import ru from "../../locales/RU/translation.json";
import MyButton from "../../components/MUI/MyButton/MyButton";

interface IUserRegister {
  fullName: string;
  password: string;
  email: string;
}

const SignUp: FC = () => {
  // Состояния - для данных пользователя регистрации
  const [userRegister, setUserRegister] = useState<IUserRegister>({
    fullName: "arsenov",
    email: "arsenov@gmail.com",
    password: "123456",
  });
  // Состояния - для загрузки ошибок
  const [errorMessage, setErrorMessage] = useState(false);

  // Состояния - для загрузки кнопки
  const [loading, setLoading] = useState(false);

  // Для - маршутизации
  const { push, locale } = useRouter();

  // Функции - для смены текста
  const t = locale === "ru" ? ru : en;

  // Отправляем post запрос
  const handleSubmit = async (value: IUserRegister) => {
    setLoading(true);
    const BASE_URL = "http://localhost:8080";

    try {
      const { data }: AxiosResponse<{ token: string }> = await axios.post(
        BASE_URL + "/auth/register",
        value
      );

      // Сохраняем токен пользователя
      localStorage.setItem("token", JSON.stringify(data.token));

      // Достаем токен пользователя
      const token = localStorage.getItem("token") ?? "";
      const parsedToken = token !== "" ? (JSON.parse(token) as string) : "";

      // Если есть токен то перенаправляем пользователя на профиль
      if (!!parsedToken) {
        push("/profile/profile");
      }
      // Сбрасываем поля объекта
      setUserRegister({
        fullName: "",
        email: "",
        password: "",
      });
    } catch ({ response }: any) {
      setErrorMessage(response.data.message);
    }
    setLoading(false);
  };

  // Для сохранения значений
  const [form] = Form.useForm();

  useEffect(() => {
    form.setFieldsValue({ ...userRegister });
  }, []);

  return (
    <section className={s.signUp}>
      <h2>{t.signUp[0]}</h2>
      <Form form={form} name="sign-up-form" onFinish={handleSubmit}>
        <Form.Item
          name="fullName"
          rules={[
            {
              required: true,
              message: t.signUp[5],
            },
          ]}
        >
          <Input prefix={<UserOutlined />} placeholder={t.signUp[1]} />
        </Form.Item>

        <Form.Item
          name="email"
          rules={[
            {
              type: "email",
              message: t.signUp[6],
            },
            {
              required: true,
              message: t.signUp[7],
            },
          ]}
        >
          <Input prefix={<MailOutlined />} placeholder={t.signUp[2]} />
        </Form.Item>
        <span className={s.error}>{errorMessage}</span>

        <Form.Item
          name="password"
          rules={[
            {
              required: true,
              message: t.signUp[8],
            },
            {
              min: 6,
              message: t.signUp[9],
            },
          ]}
        >
          <Input.Password prefix={<LockOutlined />} placeholder={t.signUp[3]} />
        </Form.Item>

        <Form.Item
          name="password"
          dependencies={["password"]}
          rules={[
            {
              required: true,
              message: t.signUp[10],
            },
            ({ getFieldValue }) => ({
              validator(_, value) {
                if (!value || getFieldValue("password") === value) {
                  return Promise.resolve();
                }
                return Promise.reject(new Error(t.signUp[11]));
              },
            }),
          ]}
        >
          <Input.Password prefix={<LockOutlined />} placeholder={t.signUp[4]} />
        </Form.Item>

        <Form.Item>
          <MyButton
            background="#7329c2"
            hoverBackground="#03d665"
            type="primary"
            loading={loading}
          >
            {t.signUp[12]}
          </MyButton>
        </Form.Item>

        <Form.Item>
          <a
            href="http://localhost:8080/oauth2/authorization/google"
            target="_blank"
          >
            {t.signUp[13]}
          </a>
        </Form.Item>

        <Form.Item>
          <a
            href="http://localhost:8080/oauth2/authorization/google"
            target="_blank"
          >
            {t.signUp[14]}
          </a>
        </Form.Item>
      </Form>
    </section>
  );
};

export default SignUp;
