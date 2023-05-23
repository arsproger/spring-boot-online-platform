import React, { useState } from "react";
import { Form, Input, Button, Select } from "antd";
import s from "./editing.module.scss";

import { useRouter } from "next/router";
import en from "../../locales/EN/translation.json";
import ru from "../../locales/RU/translation.json";
import MyButton from "../../components/MUI/MyButton/MyButton";

const { Option } = Select;

const Editing: React.FC = () => {
  // Данные пользователя
  const [userData, setUserData] = useState<any>({});

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
  const handleSubmit = async (): Promise<void> => {};

  return (
    <div className={s.editing}>
      <h1>Редактирования</h1>
      <Form onFinish={onFinish}>
        <Form.Item label="Email" name="email">
          <Input placeholder="Enter your email" />
        </Form.Item>
        <Form.Item label="Password" name="password">
          <Input.Password placeholder="Enter your password" />
        </Form.Item>
        <Form.Item label="Avatar" name="avatar">
          <Input placeholder="Enter your avatar URL" />
        </Form.Item>
        <Form.Item label="Name" name="name">
          <Input placeholder="Enter your name" />
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
              onClick={handleSubmit}
            >
              Save
            </MyButton>
            <MyButton
              background="#7329c2"
              hoverBackground="#03d665"
              type="primary"
              loading={loading}
              onClick={handleSubmit}
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
