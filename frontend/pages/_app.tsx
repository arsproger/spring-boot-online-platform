import type { AppProps } from "next/app";
import "@/styles/globals.css";
import "@/styles/RecommendedSiders.css";

import Layout from "@/components/Layout/Layout";
import { GoogleOAuthProvider } from "@react-oauth/google";

export default function App({ Component, pageProps }: AppProps) {
  return (
    <GoogleOAuthProvider clientId="<your_client_id>">
      <Layout>
        <Component {...pageProps} />
      </Layout>
    </GoogleOAuthProvider>
  );
}
