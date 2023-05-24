import React, { FC, useEffect, useState } from "react";
import s from "./signIn.module.scss";

import Link from "next/link";
import { useRouter } from "next/router";
import axios, { AxiosResponse } from "axios";
import { Form, Input } from "antd";
import { UserOutlined, LockOutlined } from "@ant-design/icons";

import en from "../../locales/EN/translation.json";
import ru from "../../locales/RU/translation.json";
import MyButton from "@/components/MUI/MyButton/MyButton";

interface IUserLogin {
  username: string;
  password: string;
}

const SignIn: FC = () => {
  // Состояния - для данных пользователя авторизации
  const [userLogin, setUserLogin] = useState<IUserLogin>({
    username: "arsenov@gmail.com",
    password: "123456",
  });
  // Состояния - для загрузки ошибок
  const [errorMessage, setErrorMessage] = useState(false);

  // Состояния - для загрузки кнопки
  const [loading, setLoading] = useState<boolean>(false);

  // Для - маршутизации
  const { push, locale } = useRouter();

  // Функции - для смены текста
  const t = locale === "ru" ? ru : en;

  // Отправляем post запрос
  const handleSubmit = async (value: IUserLogin) => {
    setLoading(true);
    const BASE_URL = "http://localhost:8080";

    try {
      const { data }: AxiosResponse<{ token: string }> = await axios.post(
        BASE_URL + "/auth/login",
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
      setUserLogin({
        username: "",
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
    form.setFieldsValue({ ...userLogin });
  }, []);

  return (
    <section className={s.signIn}>
      <h2>{t.signIn[0]}</h2>
      <Form form={form} name="sign-in-form" onFinish={handleSubmit}>
        <Form.Item
          name="username"
          rules={[
            {
              type: "email",
              message: t.signIn[3],
            },
            {
              required: true,
              message: t.signIn[4],
            },
          ]}
        >
          <Input prefix={<UserOutlined />} placeholder={t.signIn[1]} />
        </Form.Item>
        <span className={s.error}>{errorMessage}</span>

        <Form.Item
          name="password"
          rules={[
            {
              required: true,
              message: t.signIn[5],
            },
          ]}
        >
          <Input.Password prefix={<LockOutlined />} placeholder={t.signIn[2]} />
        </Form.Item>

        <Form.Item>
          <MyButton
            background="#03d665"
            hoverBackground="#7329c2"
            type="primary"
            htmlType="submit"
            loading={loading}
          >
            {t.signIn[6]}
          </MyButton>
        </Form.Item>

        <Form.Item>
          <a
            href="http://localhost:8080/oauth2/authorization/google"
            target="_blank"
          >
            {t.signIn[7]}
          </a>
        </Form.Item>

        <Form.Item>
          <a
            href="http://localhost:8080/oauth2/authorization/google"
            target="_blank"
          >
            {t.signIn[8]}
          </a>
        </Form.Item>

        <Form.Item>
          <Link href="/passwordRecovery/passwordRecovery">{t.signIn[9]}</Link>
        </Form.Item>
      </Form>
    </section>
  );
};

export default SignIn;
