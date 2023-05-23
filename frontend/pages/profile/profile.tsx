import React, { useEffect, useState } from "react";
import s from "./profile.module.scss";

import Link from "next/link";
import Image from "next/image";
import { useRouter } from "next/router";
import axios from "axios";
import { EditOutlined, LogoutOutlined } from "@ant-design/icons";
import cover from "../../public/cover.png";

import Loading from "../../components/Loading/Loading";
import en from "../../locales/EN/translation.json";
import ru from "../../locales/RU/translation.json";
import MyButton from "../../components/MUI/MyButton/MyButton";


const Profile = () => {
  // Данные пользователя
  const [userData, setUserData] = useState<any>({});

  // Состояния - для загрузки
  const [isLoading, setIsLoading] = useState(true);

  // Для - маршутизации
  const { push, locale, pathname } = useRouter();

  // Функции - для смены текста
  const t = locale === "ru" ? ru : en;

  // Выйти из аккаунта
  const signOut = () => {
    push("/");
    localStorage.removeItem("token");
    setUserData({});
  };

  // Отправляет get запрос для получения пользователя
  const getUser = async (): Promise<void> => {
    // Достаем токен пользователя
    const token = localStorage.getItem("token") ?? "";
    const parsedToken = token !== "" ? (JSON.parse(token) as string) : "";

    try {
      setIsLoading(false);
      const { data } = await axios.get("http://localhost:8080/profile", {
        headers: { Authorization: `Bearer ${parsedToken}` },
      });

      // Сохраняем данные пользователя
      setUserData(data.data);
    } catch (error) {
      console.log(error);
    }
    setIsLoading(true);
  };

  // Отправляет get запрос при каждом изменении location
  useEffect(() => {
    getUser();
  }, [pathname === "/profile/profile"]);

  return (
    <div className={s.profile}>
      <div className={s.profile__body}>
        <Image className={s.coverFirst} src={cover} alt="cover" />
        <div className={s.coverSecond}></div>
        {isLoading ? (
          <div className={s.profile__content}>
            <span className={s.avatar}>
              <Image src={userData.avatar} alt="avatar" />
            </span>
            <div className={s.container}>
              <div className={s.name}>
                <h1>{userData.username}</h1>
                <p>{userData.email}</p>
              </div>

              <Link href={"/editing/editing"}>
                <MyButton
                  background="#03d665"
                  hoverBackground="#7329c2"
                  type="primary"
                  icon={<EditOutlined />}
                >
                  Редактировать
                </MyButton>
              </Link>
            </div>
            <div className={s.text}>
              <p>
                {userData.about}
                {/* Просто по умолчанию поставил */}
                Рыбатекст используется дизайнерами, проектировщиками и
                фронтендерами, когда нужно быстро заполнить макеты или прототипы
                содержимым. Это тестовый контент, который не должен нести
                никакого смысла, лишь показать наличие самого текста или
                продемонстрировать типографику в деле.
              </p>
            </div>

            <MyButton
              background="#03d665"
              hoverBackground="#7329c2"
              type="primary"
              icon={<LogoutOutlined />}
              onClick={signOut}
            >
              Выйти
            </MyButton>
          </div>
        ) : (
          <div className={s.loading}>
            <Loading />
          </div>
        )}
      </div>
    </div>
  );
};

export default Profile;
