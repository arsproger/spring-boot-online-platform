/** @type {import('next').NextConfig} */
const nextConfig = {
  reactStrictMode: true,
  images: {
    domains: ["img.freepik.com"],
  },
  i18n: {
    locales: ["en", "ru"],
    defaultLocale: "en",
  },
};

module.exports = nextConfig;
