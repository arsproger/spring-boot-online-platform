import React, { FC } from "react";

import { Button, Col, Modal, Row } from "antd";
import { useRouter } from "next/router";
import en from "../../../locales/EN/translation.json";
import ru from "../../../locales/RU/translation.json";

interface MyModalProps {
  isModalOpen: boolean;
  setIsModalOpen: (active: boolean) => void;
}

const MyModal: FC<MyModalProps> = ({ isModalOpen, setIsModalOpen }) => {
  // Функции - для смены текста
  const { locale, push } = useRouter();
  const t = locale === "en" ? en : ru;

  // Функции - для смены url
  const handleClick = (locale: string) => {
    push("/", "/", { locale });
  };

  return (
    <Modal
      title="Выбрать язык"
      open={isModalOpen}
      footer={null}
      onCancel={() => setIsModalOpen(false)}
    >
      <Row justify="center" gutter={[20, 30]} style={{ padding: 10 }}>
        <Col xs={24} sm={12} md={8}>
          <Button onClick={() => handleClick("en")} icon={"🇺🇸 "} block>
            English
          </Button>
        </Col>
        <Col xs={24} sm={12} md={8}>
          <Button icon={"🇩🇪 "} block>
            Deutsch
          </Button>
        </Col>
        <Col xs={24} sm={12} md={8}>
          <Button onClick={() => handleClick("ru")} icon={"🇷🇺 "} block>
            Russia
          </Button>
        </Col>
        <Col xs={24} sm={12} md={8}>
          <Button icon={"🇨🇳 "} block>
            China
          </Button>
        </Col>
        <Col xs={24} sm={12} md={8}>
          <Button icon={"🇫🇷 "} block>
            France
          </Button>
        </Col>
        <Col xs={24} sm={12} md={8}>
          <Button icon={"🇺🇦 "} block>
            Ukraine
          </Button>
        </Col>
      </Row>
    </Modal>
  );
};

export default MyModal;
