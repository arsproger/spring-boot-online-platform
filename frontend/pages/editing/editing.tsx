import React, { useEffect, useState } from "react";
import { Form, Input, Button, Select } from "antd";
import s from "./editing.module.scss";

import { useRouter } from "next/router";
import axios from "axios";
import en from "../../locales/EN/translation.json";
import ru from "../../locales/RU/translation.json";
import MyButton from "../../components/MUI/MyButton/MyButton";

const { Option } = Select;

const Editing: React.FC = () => {
  // Данные пользователя
  const [userData, setUserData] = useState({
    fullName: "",
    email: "",
  });

  console.log(userData);

  // Состояния - для загрузки
  const [isLoading, setIsLoading] = useState(true);

  // Состояния - для загрузки кнопки
  const [loading, setLoading] = useState(false);

  // Функция - для загрузки кнопки
  const onFinish = () => {
    setLoading(!loading);
  };

  // Для - маршутизации
  const { push, locale } = useRouter();

  // Функции - для смены текста
  const t = locale === "ru" ? ru : en;

  // Отправляем post запрос для редактирования
  const editUser = async (): Promise<void> => {
    const BASE_URL = "http://localhost:8080";

    try {
      setIsLoading(false);
      const { data } = await axios.put(BASE_URL + `/user/${11}`, userData);

      console.log(data);

      // Сохраняем данные пользователя
      setUserData(data);
    } catch (error) {
      console.log(error);
    }
    setIsLoading(true);
  };

  return (
    <div className={s.editing}>
      <h2>Редактирования</h2>
      <Form onFinish={onFinish}>
        <Form.Item label="Name" name="name">
          <Input
            defaultValue={userData.fullName}
            placeholder="Enter your name"
            onChange={(e) => {
              setUserData({ ...userData, fullName: e.target.value });
            }}
          />
        </Form.Item>

        <Form.Item label="Email" name="email">
          <Input
            defaultValue={userData.email}
            placeholder="Enter your email"
            onChange={(e) => {
              setUserData({ ...userData, email: e.target.value });
            }}
          />
        </Form.Item>
        <Form.Item label="Password" name="password">
          <Input.Password
            placeholder="Enter your password"
         
          />
        </Form.Item>
        <Form.Item label="Avatar" name="avatar">
          <Input placeholder="Enter your avatar URL" />
        </Form.Item>
        <Form.Item label="Description" name="description">
          <Input.TextArea placeholder="Enter your description" rows={4} />
        </Form.Item>
        <Form.Item label="Role" name="role">
          <Select placeholder="Select your role">
            <Option value="admin">Admin</Option>
            <Option value="user">User</Option>
          </Select>
        </Form.Item>
        <Form.Item>
          <div className={s.buttonGroup}>
            <MyButton
              background="#7329c2"
              hoverBackground="#03d665"
              type="primary"
              loading={loading}
              onClick={editUser}
            >
              Save
            </MyButton>
            <MyButton
              background="#7329c2"
              hoverBackground="#03d665"
              type="primary"
              loading={loading}
              onClick={editUser}
            >
              Cancel
            </MyButton>
          </div>
        </Form.Item>
      </Form>
    </div>
  );
};

export default Editing;
