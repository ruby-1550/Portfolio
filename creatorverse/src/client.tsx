import { createClient } from '@supabase/supabase-js';

const URL = 'https://vgorrygyxlsekadmtnre.supabase.co';
const API_KEY = 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InZnb3JyeWd5eGxzZWthZG10bnJlIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NTYwODE3NzgsImV4cCI6MjA3MTY1Nzc3OH0.bLYUkn8twrhKIBgxF69EiaXRD449Hpalwg_V85ZlNXc';
export const supabase = createClient(URL, API_KEY);
